package org.hand.order.app.service.impl;

import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import org.hand.order.domain.entity.SoHeader;
import org.hand.order.domain.entity.content.OrderStatus;
import org.hand.order.domain.entity.dto.SoLineDTO;
import org.hand.order.domain.repository.SoHeaderRepository;
import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hand.order.app.service.SoLineService;
import org.hand.order.domain.entity.SoLine;
import org.hand.order.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 应用服务默认实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Service
public class SoLineServiceImpl extends BaseAppService implements SoLineService {

    @Resource
    private SoHeaderRepository soHeaderRepository;

    private final SoLineRepository soLineRepository;

    @Autowired
    public SoLineServiceImpl(SoLineRepository soLineRepository) {
        this.soLineRepository = soLineRepository;
    }

    @Override
    public Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest) {
        return soLineRepository.pageAndSort(pageRequest, soLine);
    }

    @Override
    public SoLine detail(Long tenantId, Long soLineId) {
        return soLineRepository.selectByPrimaryKey(soLineId);
    }

    @Override
    public SoLine create(Long tenantId, SoLine soLine) {
        validObject(soLine);
            soLineRepository.insertSelective(soLine);
        return soLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SoLine update(Long tenantId, SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
            soLineRepository.updateByPrimaryKeySelective(soLine);
        return soLine;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(SoLine soLine) {
        SecurityTokenHelper.validToken(soLine);
            soLineRepository.deleteByPrimaryKey(soLine);
    }

    /**
     * 根据订单头查询订单行
     * @param soHeaderId
     * @param pageRequest
     * @return
     */
    @Override
    public Page<SoLine> queryByHead(PageRequest pageRequest,Long soHeaderId) {
        return soLineRepository.pageAndSortBySoHeadId(pageRequest,soHeaderId);
    }

    /**
     * 根据订单头查询订单行1
     * @param soHeaderId
     * @param pageRequest
     * @return
     */
    @Override
    public Page<SoLineDTO> queryByHead1(PageRequest pageRequest, Long soHeaderId) {
        return soLineRepository.pageAndSortBySoHeadId1(pageRequest,soHeaderId);
    }

    /**
     * 根据订单行删除
     * @param soLineId
     */
    @Override
    public void deleteOrder(Long soLineId) {
        SoLine soLineExist = soLineRepository.selectByPrimaryKey(soLineId);
        if (soLineExist == null){
            throw new CommonException("单据不存在");
        }
        if (!soLineExist.getCreatedBy().equals(DetailsHelper.getUserDetails().getUserId())){
            throw new CommonException("与创始人不一致，无权限");
        }
        SoHeader soHeader = soHeaderRepository.selectByPrimaryKey(soLineExist.getSoHeaderId());
        if (!OrderStatus.NEW.equals(soHeader.getOrderStatus()) && !OrderStatus.REJECTED.equals(soHeader.getOrderStatus())){
            throw new CommonException("单据状态不为NEW 或 REJECTED！");
        }
        soLineRepository.deleteByPrimaryKey(soLineExist.getSoLineId());
    }
}
