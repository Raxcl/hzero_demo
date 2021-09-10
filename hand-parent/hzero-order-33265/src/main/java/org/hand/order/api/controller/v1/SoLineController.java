package org.hand.order.api.controller.v1;

import org.hand.order.domain.entity.dto.SoLineDTO;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hand.order.app.service.SoLineService;
import org.hand.order.domain.entity.SoLine;
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
@RestController("soLineController.v1")
@RequestMapping("/v1/{organizationId}/so-lines")
public class SoLineController extends BaseController {

    private final SoLineService soLineService;

    @Autowired
    public SoLineController(SoLineService soLineService) {
        this.soLineService = soLineService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoLine>> list(@PathVariable("organizationId") Long organizationId, SoLine soLine, @ApiIgnore @SortDefault(value = SoLine.FIELD_SO_LINE_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoLine> list = soLineService.list(organizationId, soLine, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soLineId}")
    public ResponseEntity<SoLine> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long soLineId) {
        SoLine soLine = soLineService.detail(organizationId, soLineId);
        return Results.success(soLine);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoLine> create(@PathVariable("organizationId") Long organizationId, @RequestBody SoLine soLine) {
        soLineService.create(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoLine> update(@PathVariable("organizationId") Long organizationId, @RequestBody SoLine soLine) {
        soLineService.update(organizationId, soLine);
        return Results.success(soLine);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody SoLine soLine) {
        soLineService.remove(soLine);
        return Results.success("200");
    }



    /**
     * 根据订单头查询订单行1
     * 有bug未解决--->已解决：之前GetMapping没有使用rest风格，加上就好了
     * @param soHeaderId
     * @param pageRequest
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单行查询1")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/order/queryLine1/{soHeaderId}")
    public ResponseEntity<Page<SoLineDTO>> queryByOrderLine1(
            @PathVariable Long soHeaderId,
            PageRequest pageRequest,
            @PathVariable("organizationId") Long organizationId
    ) {
        Page<SoLineDTO> list = soLineService.queryByHead1(pageRequest,soHeaderId);
        return Results.success(list);
    }

    /**
     * 根据订单头查询订单行
     * 有bug未解决--->已解决：之前GetMapping没有使用rest风格，加上就好了
     * @param soHeaderId
     * @param pageRequest
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单行查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/order/queryLine/{soHeaderId}")
    public ResponseEntity<Page<SoLine>> queryByOrderLine(
            @PathVariable Long soHeaderId,
            PageRequest pageRequest,
            @PathVariable("organizationId") Long organizationId
    ) {
        Page<SoLine> list = soLineService.queryByHead(pageRequest,soHeaderId);
        return Results.success(list);
    }

    /**
     * 根据订单行删除API
     * @param soLineId
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "根据订单行删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/order/delete/{soLineId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long soLineId, @PathVariable String organizationId) {
        soLineService.deleteOrder(soLineId);
        return Results.success("200");
    }


}
