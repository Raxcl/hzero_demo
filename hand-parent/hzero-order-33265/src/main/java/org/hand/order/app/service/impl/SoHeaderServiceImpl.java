package org.hand.order.app.service.impl;

import com.alibaba.druid.util.StringUtils;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;
import org.hand.order.api.controller.v1.OrderMessageController;
import org.hand.order.domain.entity.SoLine;
import org.hand.order.domain.entity.content.OrderStatus;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;
import org.hand.order.domain.entity.vo.SoLineVO;
import org.hand.order.domain.entity.vo.SoOrderVO;
import org.hand.order.domain.repository.SoLineRepository;
import org.hand.order.infra.mapper.SoHeaderMapper;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.boot.platform.plugin.hr.EmployeeHelper;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.core.base.BaseAppService;

import org.hzero.core.exception.CheckedException;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hand.order.app.service.SoHeaderService;
import org.hand.order.domain.entity.SoHeader;
import org.hand.order.domain.repository.SoHeaderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 应用服务默认实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Service
public class SoHeaderServiceImpl extends BaseAppService implements SoHeaderService {

    @Resource
    private OrderMessageController orderMessageController;

    @Autowired
    private WorkflowClient workflowClient;

    @Resource
    private SoLineRepository soLineRepository;

    @Resource
    private SoHeaderMapper soHeaderMapper;

    @Resource
    private CodeRuleBuilder codeRuleBuilder;

    private final SoHeaderRepository soHeaderRepository;

    @Autowired
    public SoHeaderServiceImpl(SoHeaderRepository soHeaderRepository) {
        this.soHeaderRepository = soHeaderRepository;
    }


    @Override
    public Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest) {
        return soHeaderRepository.pageAndSort(pageRequest, soHeader);
    }

    @Override
    public SoHeader detail(Long tenantId, Long soHeaderId) {
        return soHeaderRepository.selectByPrimaryKey(soHeaderId);
    }



    @Override
    public SoHeader create(Long tenantId, SoHeader soHeader) {
        validObject(soHeader);
        soHeaderRepository.insertSelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoHeader update(Long tenantId, SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.updateByPrimaryKeySelective(soHeader);
        return soHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(SoHeader soHeader) {
        SecurityTokenHelper.validToken(soHeader);
        soHeaderRepository.deleteByPrimaryKey(soHeader);
    }


    /**
     * 多条件查询
     * @param pageRequest 分页
     * @param soHeaderParam
     * @return
     */
    @Override
    public Page<SoHeaderDTO> queryByOrderTotal(PageRequest pageRequest, SoHeaderParam soHeaderParam) {
        return soHeaderRepository.orderByQuery(pageRequest, soHeaderParam);
    }

    /**
     * 测试案例
     * @param tenantId
     * @param soHeaderId
     * @return
     */
    @Override
    public SoHeaderDTO queryBySoHeaderId(Long tenantId, Long soHeaderId) {
        SoHeaderDTO soHeaderDTO = soHeaderRepository.queryBySoHeaderId(soHeaderId);
        return soHeaderDTO;
    }

    /**
     * 订单信息保存
     * @param soOrderVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoOrderVO saveOrder(SoOrderVO soOrderVO) {
        SoOrderVO soOrderVoNew = new SoOrderVO();
        //判断订单头是否为空,为空新增，不为空修改
        if (soOrderVO.getSoHeaderId() == null || soOrderVO.getSoHeaderId() == 0) {
            //订单头和订单行的新增
            String date = "2021-01-01";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //判断日期不小于2021-01-01
                if (soOrderVO.getOrderDate().compareTo(format.parse(date)) < 0) {
                    throw new CommonException("无法创建当前日期订单！");
                }
            } catch (ParseException e) {
                throw new CommonException("无法创建当前日期订单！");
            }
            //flag 为 0 执行插入操作
            soOrderVoNew = insertOrUpdateOrder(soOrderVO, 0);
        } else {
            //订单头和订单行的修改
            //flag为1执行更新操作
            soOrderVoNew = insertOrUpdateOrder(soOrderVO, 1);
        }
        return soOrderVoNew;
    }

    private SoOrderVO insertOrUpdateOrder(SoOrderVO soOrderVO, int flag) {
        String orderCode;
        //生成新的订单编码
        orderCode = codeRuleBuilder.generateCode("HZERO.33265.NUMBER", null);
        soOrderVO.setOrderNumber(orderCode);
        SoHeader soHeader = new SoHeader();
        //订单头和订单行的新增
        if (flag == 0) {
            //订单头的新增
            soHeader.setOrderNumber(orderCode);
            soOrderVO.setOrderStatus("NEW");
            soHeaderRepository.insert(soHeader);
            soOrderVO.setSoHeaderId(soHeader.getSoHeaderId());
            BeanUtils.copyProperties(soOrderVO,soHeader);


            //订单行的新增
            //判断行id是否为空，不为空则报错
            for (SoLineVO soLineVO : soOrderVO.getSoLineList()) {
                if (soLineVO.getSoLineId() != null) {
                    throw new CommonException("订单行id不为空，不能新增,请不要输入订单行id");
                }
                SoLine soLine = new SoLine();
                soLine.setSoHeaderId(soHeader.getSoHeaderId());
                BeanUtils.copyProperties(soOrderVO,soLine);
                soLineRepository.insert(soLine);
            }
        }
        //订单头和订单行的修改
        if (flag == 1) {
            //修改订单头的校验
            soHeader = soHeaderRepository.selectByPrimaryKey(soOrderVO.getSoHeaderId());
            //判断订单状态是否为new或者rejected
            if (!OrderStatus.NEW.equals(soHeader.getOrderStatus()) && !OrderStatus.REJECTED.equals(soHeader.getOrderStatus())) {
                throw new CommonException("单据状态不为NEW或REJECTED，不能修改订单头");
            }
            //权限判断
            if (!soHeader.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
                throw new CommonException("没有权限！不能修改订单头！");
            }
            //开始修改订单头
            soHeader.setOrderNumber(orderCode);
            BeanUtils.copyProperties(soOrderVO,soHeader);
            soHeaderRepository.updateByPrimaryKey(soHeader);

            //修改订单行的校验
            //判断行id是否为空，为空则报错
            for (SoLineVO soLineVO : soOrderVO.getSoLineList()) {
                if (soLineVO.getSoLineId() == null) {
                    throw new CommonException("订单行id为空，不能修改,请输入订单行id");
                }
                SoLine soLine = new SoLine();
                soLine = soLineRepository.selectByPrimaryKey(soLineVO.getSoLineId());
                soLine.setSoHeaderId(soHeader.getSoHeaderId());
                BeanUtils.copyProperties(soOrderVO,soLine);
                soLineRepository.updateByPrimaryKey(soLine);
            }
        }
        return soOrderVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByOrderId(SoHeader soHeader) {
        SoHeader soHeaderExist = soHeaderRepository.selectByPrimaryKey(soHeader.getSoHeaderId());
        if (soHeaderExist == null) {
            throw new CommonException("单据不存在!");
        }
        if (!soHeaderExist.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
            throw new CommonException("您没有权限删除此订单！");
        }
        //判断订单状态
        if (!OrderStatus.NEW.equals(soHeaderExist.getOrderStatus()) && !OrderStatus.REJECTED.equals((soHeaderExist.getOrderStatus()))) {
            throw new CommonException("单据状态不为NEW或REJECTED");
        }
        //一个订单头有可能对应多个订单行，根据订单头删除对应订单行，然后删除订单头
        List<SoLine> soLines = soLineRepository.select("soHeaderId", soHeaderExist.getSoHeaderId());
        if (soLines.size() > 0) {
            for (SoLine soLine : soLines) {
                soLineRepository.deleteByPrimaryKey(soLine.getSoLineId());
            }
        }
        soHeaderRepository.deleteByPrimaryKey(soHeaderExist.getSoHeaderId());
    }

    /**
     * 订单状态更新
     *
     * @param soHeaderId
     * @param orderStatus
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByStatus(Long soHeaderId, String orderStatus) {
        //获取当前登录用户信息
        CustomUserDetails self = DetailsHelper.getUserDetails();
        //当前角色ID
        Long roleId = self.getRoleId();

        SoHeader soHeaderExist = soHeaderRepository.selectByPrimaryKey(soHeaderId);
        //校验订单是否存在
        if (soHeaderExist == null) {
            throw new CommonException("订单不存在");
        }
        //提交状态
        if (OrderStatus.SUBMITED.equals(orderStatus)) {
            //校验当前用户是否与单据创始人一致
            if (!soHeaderExist.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())) {
                throw new CommonException("当前用户是否与单据创始人不一致");
            }
            //校验当前单据数据库状态是否为NEW/REJECTED
            if (!OrderStatus.NEW.equals(soHeaderExist.getOrderStatus()) && !OrderStatus.REJECTED.equals(soHeaderExist.getOrderStatus())) {
                throw new CommonException("当前单据数据库状态不为NEW/REJECTED::::::");
            }
        }
        //审批状态
        String sale = "sale_manager_33265";
        if (OrderStatus.APPROVED.equals(orderStatus)) {
            //校验当前用户角色是否为SALE_MANAGER_xxxx
            /*if (!roleId.equals(sale)) {
                throw new CommonException("当前用户角色不为sale_manager_33265");
            }*/
            //校验当前单据数据库状态是否为SUBMITED
            if (!OrderStatus.SUBMITED.equals(soHeaderExist.getOrderStatus())) {
                throw new CommonException("当前单据数据库状态不为SUBMITED");
            }

        }
        //拒绝状态
        if (OrderStatus.REJECTED.equals(orderStatus)) {
            //校验当前用户角色是否为SALE_MANAGER_xxxx
            /*if (!roleId.equals(sale)) {
                throw new CommonException("当前用户角色不为sale_manager_33265");
            }*/
            //校验当前单据数据库状态是否为SUBMITED
            if (!OrderStatus.SUBMITED.equals(soHeaderExist.getOrderStatus())) {
                throw new CommonException("当前单据数据库状态不为SUBMITED");
            }
        }
        //更新订单状态
        soHeaderExist.setOrderStatus(orderStatus);
        soHeaderRepository.updateByPrimaryKey(soHeaderExist);
    }

    @Override
    //@Transactional(rollbackFor = Exception.class)
    public void orderStatusWorkflow(long organizationId, Long soHeaderId) throws CheckedException {
        SoHeader soHeader = soHeaderRepository.selectByPrimaryKey(soHeaderId);
        // 对订单进行校验(虽然前端已经进行了提交按钮控制，但不能完全刹住，还是需要我们进行校验),订单状态为 NEW/REJECTED 才能进行提交审批
        // （ 按理来说，提交审批人是创建人才可以进行提交审批，文档没有规定，这里进行没有进行校验，后续加上 ）
        if (OrderStatus.SUBMITED.equals(soHeader.getOrderStatus())
                && !OrderStatus.NEW.equals(soHeader.getOrderStatus())) {
            throw new CheckedException("订单编号:" + soHeader.getOrderNumber() + "不是新增或已拒绝,提交审批失败");
        }
        /**
         * @param organizationId 租户ID
         * @param flowKey 流程定义编码
         * @param businessKey 业务主键
         * @param startEmpNum 流程启动人（员工编码）
         * @param variableMap 启动参数（流程描述：flowDesc；其它流程中使用到的变量参数）
         */
        String startEmpNum = EmployeeHelper.getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId);
        if (StringUtils.isEmpty(startEmpNum)) {
            throw new CheckedException("当前用户不在公司内不可以进行提交审批");
        }
        String flowKey = OrderStatus.ORDER_WORKFLOW_FLOWKEY;
        String businessKey = soHeader.getOrderNumber();
        HashMap<String, Object> variableMap = new HashMap<>();
        variableMap.put("soHeaderId", soHeaderId);
        workflowClient.startInstanceByFlowKey(organizationId, flowKey, businessKey, startEmpNum, variableMap);
        // 在启动后订单头状态改变为 SUBMIT
        this.updateByStatus(soHeaderId, OrderStatus.SUBMITED);
    }

    @Override
    public void workflowChangeStatus(Long organizationId, Long soHeaderId, String status) {
        this.updateByStatus(soHeaderId, status);
        orderMessageController.hello(status);
    }


}
