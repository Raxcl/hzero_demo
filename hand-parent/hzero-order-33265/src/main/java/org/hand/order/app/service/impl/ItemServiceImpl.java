package org.hand.order.app.service.impl;

import org.hzero.core.base.BaseAppService;

import org.hzero.mybatis.helper.SecurityTokenHelper;

import org.hand.order.app.service.ItemService;
import org.hand.order.domain.entity.Item;
import org.hand.order.domain.repository.ItemRepository;
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
public class ItemServiceImpl extends BaseAppService implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> list(Long tenantId, Item item, PageRequest pageRequest) {
        return itemRepository.pageAndSort(pageRequest, item);
    }

    @Override
    public Item detail(Long tenantId, Long itemId) {
        return itemRepository.selectByPrimaryKey(itemId);
    }

    @Override
    public Item create(Long tenantId, Item item) {
        validObject(item);
            itemRepository.insertSelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Item update(Long tenantId, Item item) {
        SecurityTokenHelper.validToken(item);
            itemRepository.updateByPrimaryKeySelective(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Item item) {
        SecurityTokenHelper.validToken(item);
            itemRepository.deleteByPrimaryKey(item);
    }
}
