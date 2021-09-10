package org.hand.training.api.controller.v1;

import io.swagger.annotations.Api;
import org.hand.training.config.SwaggerTags;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hand.training.app.service.IamUserService;
import org.hand.training.domain.entity.IamUser;
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
 * @author chen.long08@hand-china.com 2021-07-28 15:14:35
 */
@Api(tags = SwaggerTags.IAM_USER)
@RestController("iamUserController.v1")
@RequestMapping("/v1/{organizationId}/iam-users")
public class IamUserController extends BaseController {

    private final IamUserService iamUserService;

    @Autowired
    public IamUserController(IamUserService iamUserService) {
        this.iamUserService = iamUserService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<IamUser>> list(@PathVariable("organizationId") Long organizationId, IamUser iamUser, @ApiIgnore @SortDefault(value = IamUser.FIELD_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<IamUser> list = iamUserService.list(organizationId, iamUser, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{id}")
    public ResponseEntity<IamUser> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long id) {
        IamUser iamUser = iamUserService.detail(organizationId, id);
        return Results.success(iamUser);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<IamUser> create(@PathVariable("organizationId") Long organizationId, @RequestBody IamUser iamUser) {
        iamUserService.create(organizationId, iamUser);
        return Results.success(iamUser);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<IamUser> update(@PathVariable("organizationId") Long organizationId, @RequestBody IamUser iamUser) {
        iamUserService.update(organizationId, iamUser);
        return Results.success(iamUser);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody IamUser iamUser) {
        iamUserService.remove(iamUser);
        return Results.success();
    }

}
