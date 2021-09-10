package org.hand.order.app.service;

import org.hand.order.domain.entity.SoHeader;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;
import org.hand.order.domain.entity.vo.SoOrderVO;
import org.hzero.core.exception.CheckedException;

/**
 * 应用服务
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoHeaderService {

    /**
     * 查询参数
     *
     * @param tenantId    租户ID
     * @param soHeader
     * @param pageRequest 分页
     * @return 列表
     */
    Page<SoHeader> list(Long tenantId, SoHeader soHeader, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId   租户ID
     * @param soHeaderId 主键
     * @return 列表
     */
    SoHeader detail(Long tenantId, Long soHeaderId);


    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param soHeader
     * @return
     */
    SoHeader create(Long tenantId, SoHeader soHeader);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param soHeader
     * @return
     */
    SoHeader update(Long tenantId, SoHeader soHeader);

    /**
     * 删除
     *
     * @param soHeader
     */
    void remove(SoHeader soHeader);

    /**
     * 多条件查询参数
     *
     * @param soHeaderParam
     * @param pageRequest 分页
     * @return 列表
     */
    Page<SoHeaderDTO> queryByOrderTotal( PageRequest pageRequest,SoHeaderParam soHeaderParam);


    /**
     * 根据订单头查询信息
     * @param tenantId
     * @param soHeaderId
     * @return
     */
    SoHeaderDTO queryBySoHeaderId(Long tenantId, Long soHeaderId);


    /**
     * 订单保存
     * @param soOrderVO
     * @return
     */
    SoOrderVO saveOrder(SoOrderVO soOrderVO);

    /**
     * 根据订单头id删除订单信息
     * @param soHeader
     */
    void deleteByOrderId(SoHeader soHeader);

    /**
     * 更新订单状态
     * @param soHeaderId
     * @param orderStatus
     */
    void updateByStatus(Long soHeaderId, String orderStatus);

    void orderStatusWorkflow(long organizationId, Long soHeaderId) throws CheckedException;

    void workflowChangeStatus(Long organizationId, Long soHeaderId, String status);
}
