package org.hand.order.app.service;

import org.hand.order.domain.entity.SoLine;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.order.domain.entity.dto.SoLineDTO;

/**
 * 应用服务
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoLineService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<SoLine> list(Long tenantId, SoLine soLine, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param soLineId 主键
     * @return 列表
     */
        SoLine detail(Long tenantId, Long soLineId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @return 
     */
        SoLine create(Long tenantId, SoLine soLine);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param soLine 
     * @return 
     */
        SoLine update(Long tenantId, SoLine soLine);

    /**
     * 删除
     *
     * @param soLine 
     */
    void remove(SoLine soLine);

    /**
     * 根据订单头查询订单行
     * @param soHeaderId
     * @param pageRequest
     * @return
     */
    Page<SoLine> queryByHead(PageRequest pageRequest,Long soHeaderId);

    /**
     * 根据订单头查询订单行1
     * @param soHeaderId
     * @param pageRequest
     * @return
     */
    Page<SoLineDTO> queryByHead1(PageRequest pageRequest, Long soHeaderId);

    /**
     * 根据订单行删除
     * @param soLineId
     */
    void deleteOrder(Long soLineId);
}
