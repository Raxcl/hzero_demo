package org.hand.order.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;

import org.hand.order.domain.entity.Customer;
import org.hand.order.domain.repository.CustomerRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Component
public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> implements CustomerRepository {


}
