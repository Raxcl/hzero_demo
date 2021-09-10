package org.hand.order.infra.repository.impl;

import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;
import org.hand.order.infra.mapper.SoHeaderMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hand.order.domain.entity.SoHeader;
import org.hand.order.domain.repository.SoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源库实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Component
public class SoHeaderRepositoryImpl extends BaseRepositoryImpl<SoHeader> implements SoHeaderRepository {


    private final SoHeaderMapper soHeaderMapper;

    @Autowired
    public SoHeaderRepositoryImpl(SoHeaderMapper soHeaderMapper){
        this.soHeaderMapper = soHeaderMapper;
    }


    /**
     * 多条件查询
     * @param soHeaderParam
     * @return
     */
    @Override
    public List<SoHeaderDTO> queryByOrderTotal(SoHeaderParam soHeaderParam) {

        List<SoHeaderDTO> list = soHeaderMapper.queryByOrderTotal(soHeaderParam);
        return list;
    }

    /**
     * 测试类
     */
    @Override
    public SoHeaderDTO queryBySoHeaderId(Long soHeaderId) {
        SoHeaderDTO soHeaderDTO = soHeaderMapper.queryBySoHeaderId(soHeaderId);
        return soHeaderDTO;
    }


}
