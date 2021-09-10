package org.hand.order.infra.repository.impl;

import org.hand.order.infra.mapper.CompanyMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hand.order.domain.entity.Company;
import org.hand.order.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Component
public class CompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements CompanyRepository {


}
