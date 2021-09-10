package org.hand.order.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hand.order.app.service.ItemService;
import org.hand.order.domain.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 管理 API
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@RestController("itemController.v1")
@RequestMapping("/v1/{organizationId}/items")
public class ItemController extends BaseController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<Item>> list(@PathVariable("organizationId") Long organizationId, Item item, @ApiIgnore @SortDefault(value = Item.FIELD_ITEM_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<Item> list = itemService.list(organizationId, item, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long itemId) {
        Item item = itemService.detail(organizationId, itemId);
        return Results.success(item);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<Item> create(@PathVariable("organizationId") Long organizationId, @RequestBody Item item) {
        itemService.create(organizationId, item);
        return Results.success(item);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<Item> update(@PathVariable("organizationId") Long organizationId, @RequestBody Item item) {
        itemService.update(organizationId, item);
        return Results.success(item);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Item item) {
        itemService.remove(item);
        return Results.success("200");
    }

}
