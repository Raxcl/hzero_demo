package org.hand.order.app.service;

import org.hand.order.domain.entity.Customer;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface CustomerService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param customer 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<Customer> list(Long tenantId, Customer customer, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param customerId 主键
     * @return 列表
     */
        Customer detail(Long tenantId, Long customerId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param customer 
     * @return 
     */
        Customer create(Long tenantId, Customer customer);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param customer 
     * @return 
     */
        Customer update(Long tenantId, Customer customer);

    /**
     * 删除
     *
     * @param customer 
     */
    void remove(Customer customer);
}
