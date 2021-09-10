package org.hand.order.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;
import org.hzero.mybatis.base.BaseRepository;

import org.hand.order.domain.entity.SoHeader;

import java.util.List;

/**
 * 资源库
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoHeaderRepository extends BaseRepository<SoHeader> {


    /**
     * 分页并且查询
     * @param pageRequest
     * @param soHeaderParam
     * @return
     */
    default Page<SoHeaderDTO> orderByQuery(PageRequest pageRequest, SoHeaderParam soHeaderParam) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
            return this.queryByOrderTotal(soHeaderParam);
        });
    }

    /**
     * 订单查询汇总
     * @param soHeaderParam
     * @return
     */
    List<SoHeaderDTO> queryByOrderTotal(SoHeaderParam soHeaderParam);

    /**
     * 订单头查询
     * @param soHeaderId
     * @return
     */
    SoHeaderDTO queryBySoHeaderId(Long soHeaderId);
}
