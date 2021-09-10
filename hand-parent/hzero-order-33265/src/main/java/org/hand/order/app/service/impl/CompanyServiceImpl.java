package org.hand.order.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hand.order.app.service.CompanyService;
import org.hand.order.domain.entity.Company;
import org.hand.order.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务默认实现
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Service
public class CompanyServiceImpl extends BaseAppService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Company> list(Long tenantId, Company company, PageRequest pageRequest) {
        return companyRepository.pageAndSort(pageRequest, company);
    }

    @Override
    public Company detail(Long tenantId, Long companyId) {
        return companyRepository.selectByPrimaryKey(companyId);
    }

    @Override
    public Company create(Long tenantId, Company company) {
        validObject(company);
            companyRepository.insertSelective(company);
        return company;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Company update(Long tenantId, Company company) {
        SecurityTokenHelper.validToken(company);
            companyRepository.updateByPrimaryKeySelective(company);
        return company;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Company company) {
        SecurityTokenHelper.validToken(company);
            companyRepository.deleteByPrimaryKey(company);
    }
}
