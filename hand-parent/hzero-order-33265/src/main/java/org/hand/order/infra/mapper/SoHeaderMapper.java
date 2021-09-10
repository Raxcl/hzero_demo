package org.hand.order.infra.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.hand.order.domain.entity.SoHeader;
import io.choerodon.mybatis.common.BaseMapper;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;

import java.util.List;

/**
 * Mapper
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoHeaderMapper extends BaseMapper<SoHeader> {
    /**
     * 测试接口
     * @param soHeaderId
     * @return
     */
    SoHeaderDTO queryBySoHeaderId(Long soHeaderId);


    /**
     * 多条件查询
     * @param soHeaderParam
     * @return
     */
    List<SoHeaderDTO> queryByOrderTotal(@Param("soHeaderParam") SoHeaderParam soHeaderParam);

    /**
     * 每天凌晨3点 ，定时将销售订单状态为已审批的修改为已关闭
     */
    void updateByTime();


}
