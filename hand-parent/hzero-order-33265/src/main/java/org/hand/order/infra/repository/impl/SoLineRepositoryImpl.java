package org.hand.order.infra.repository.impl;

import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.dto.SoLineDTO;
import org.hand.order.infra.mapper.SoHeaderMapper;
import org.hand.order.infra.mapper.SoLineMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hand.order.domain.entity.SoLine;
import org.hand.order.domain.repository.SoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  资源库实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Component
public class SoLineRepositoryImpl extends BaseRepositoryImpl<SoLine> implements SoLineRepository {

    private final SoLineMapper soLineMapper;

    @Autowired
    public SoLineRepositoryImpl(SoLineMapper soLineMapper){
        this.soLineMapper = soLineMapper;
    }


    @Override
    public List<SoLine> selectBySoHeadId(Long soHeaderId) {
        List<SoLine> list = soLineMapper.selectBySoHeadId(soHeaderId);
        return list;
    }

    @Override
    public List<SoLineDTO> selectBySoHeadId1(Long soHeaderId) {
        List<SoLineDTO> list = soLineMapper.selectBySoHeadId1(soHeaderId);
        return list;
    }
}
