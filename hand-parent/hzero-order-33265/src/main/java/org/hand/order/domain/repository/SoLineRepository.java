package org.hand.order.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hand.order.domain.entity.dto.SoLineDTO;
import org.hzero.mybatis.base.BaseRepository;

import org.hand.order.domain.entity.SoLine;

import java.util.List;

/**
 * 资源库
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface SoLineRepository extends BaseRepository<SoLine> {

    /**
     * 分页方法
     * @param pageRequest
     * @param soHeaderId
     * @return
     */
    default Page<SoLine> pageAndSortBySoHeadId(PageRequest pageRequest,Long soHeaderId) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
            return this.selectBySoHeadId(soHeaderId);
        });
    }

    /**
     * 根据订单头查询订单行
     * @param soHeaderId
     * @return
     */
    List<SoLine> selectBySoHeadId(Long soHeaderId);

    /**
     * 分页方法1
     * @param pageRequest
     * @param soHeaderId
     * @return
     */
    default Page<SoLineDTO> pageAndSortBySoHeadId1(PageRequest pageRequest, Long soHeaderId) {
        return PageHelper.doPageAndSort(pageRequest, () -> {
            return this.selectBySoHeadId1(soHeaderId);
        });
    }

    /**
     * 根据订单头查询订单行1
     * @param soHeaderId
     * @return
     */
    List<SoLineDTO> selectBySoHeadId1(Long soHeaderId);
}
