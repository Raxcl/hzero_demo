package org.hand.training.app.service;

import org.hand.training.domain.entity.IamUser;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 应用服务
 *
 * @author chen.long08@hand-china.com 2021-07-28 15:14:35
 */
public interface IamUserService {

    /**
     * 查询参数
     *
     * @param tenantId 租户ID
     * @param iamUser 
     * @param pageRequest 分页
     * @return 列表
     */
    Page<IamUser> list(Long tenantId, IamUser iamUser, PageRequest pageRequest);

    /**
     * 详情
     *
     * @param tenantId 租户ID
     * @param id 主键
     * @return 列表
     */
        IamUser detail(Long tenantId, Long id);

    /**
     * 创建
     *
     * @param tenantId 租户ID
     * @param iamUser 
     * @return 
     */
        IamUser create(Long tenantId, IamUser iamUser);

    /**
     * 更新
     *
     * @param tenantId 租户ID
     * @param iamUser 
     * @return 
     */
        IamUser update(Long tenantId, IamUser iamUser);

    /**
     * 删除
     *
     * @param iamUser 
     */
    void remove(IamUser iamUser);
}
