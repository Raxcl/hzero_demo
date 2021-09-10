package org.hand.training.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hand.training.app.service.IamUserService;
import org.hand.training.domain.entity.IamUser;
import org.hand.training.domain.repository.IamUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务默认实现
 *
 * @author chen.long08@hand-china.com 2021-07-28 15:14:35
 */
@Service
public class IamUserServiceImpl extends BaseAppService implements IamUserService {

    private final IamUserRepository iamUserRepository;

    @Autowired
    public IamUserServiceImpl(IamUserRepository iamUserRepository) {
        this.iamUserRepository = iamUserRepository;
    }

    @Override
    public Page<IamUser> list(Long tenantId, IamUser iamUser, PageRequest pageRequest) {
        return iamUserRepository.pageAndSort(pageRequest, iamUser);
    }

    @Override
    public IamUser detail(Long tenantId, Long id) {
        return iamUserRepository.selectByPrimaryKey(id);
    }

    @Override
    public IamUser create(Long tenantId, IamUser iamUser) {
        validObject(iamUser);
            iamUserRepository.insertSelective(iamUser);
        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IamUser update(Long tenantId, IamUser iamUser) {
        SecurityTokenHelper.validToken(iamUser);
            iamUserRepository.updateByPrimaryKeySelective(iamUser);
        return iamUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(IamUser iamUser) {
        SecurityTokenHelper.validToken(iamUser);
            iamUserRepository.deleteByPrimaryKey(iamUser);
    }
}
