package org.hand.order.app.service;

import org.hand.order.domain.entity.Company;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
public interface CompanyService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param company 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<Company> list(Long tenantId, Company company, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param companyId 主键
     * @return 列表
     */
        Company detail(Long tenantId, Long companyId);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param company 
     * @return 
     */
        Company create(Long tenantId, Company company);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param company 
     * @return 
     */
        Company update(Long tenantId, Company company);

    /**
     * 删除
     *
     * @param company 
     */
    void remove(Company company);
}
