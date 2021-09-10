package org.hand.order.infra.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.hand.order.domain.entity.SoLine;
import io.choerodon.mybatis.common.BaseMapper;
import org.hand.order.domain.entity.dto.SoLineDTO;

import java.util.List;

/**
 * Mapper
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoLineMapper extends BaseMapper<SoLine> {

    /**
     * 根据订单头查询订单行信息
     * @param soHeaderId
     * @return
     */
    List<SoLine> selectBySoHeadId(@Param("soHeaderId") Long soHeaderId);

    /**
     * 根据订单头查询订单行信息1
     * @param soHeaderId
     * @return
     */
    List<SoLineDTO> selectBySoHeadId1(@Param("soHeaderId") Long soHeaderId);
}
