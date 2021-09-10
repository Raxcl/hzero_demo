package org.hand.order.api.controller.v1;

import org.hand.order.domain.entity.SoLine;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.domain.entity.param.SoHeaderParam;
import org.hand.order.domain.entity.vo.SoOrderVO;
import org.hand.order.domain.repository.SoHeaderRepository;
import org.hzero.core.exception.CheckedException;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;

import org.hand.order.app.service.SoHeaderService;
import org.hand.order.domain.entity.SoHeader;
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

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理 API
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@RestController("soHeaderController.v1")
@RequestMapping("/v1/{organizationId}/so-headers")
public class SoHeaderController extends BaseController {

    @Resource
    private SoHeaderRepository soHeaderRepository;

    private final SoHeaderService soHeaderService;

    @Autowired
    public SoHeaderController(SoHeaderService soHeaderService) {
        this.soHeaderService = soHeaderService;
    }

    @ApiOperation(value = "列表")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping
    public ResponseEntity<Page<SoHeader>> list(@PathVariable("organizationId") Long organizationId, SoHeader soHeader, @ApiIgnore @SortDefault(value = SoHeader.FIELD_SO_HEADER_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<SoHeader> list = soHeaderService.list(organizationId, soHeader, pageRequest);
        return Results.success(list);
    }


    @ApiOperation(value = "明细")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/{soHeaderId}")
    public ResponseEntity<SoHeader> detail(@PathVariable("organizationId") Long organizationId, @PathVariable Long soHeaderId) {
        SoHeader soHeader = soHeaderService.detail(organizationId, soHeaderId);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "创建")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping
    public ResponseEntity<SoHeader> create(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.create(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "修改")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping
    public ResponseEntity<SoHeader> update(@PathVariable("organizationId") Long organizationId, @RequestBody SoHeader soHeader) {
        soHeaderService.update(organizationId, soHeader);
        return Results.success(soHeader);
    }

    @ApiOperation(value = "删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody SoHeader soHeader) {
        soHeaderService.remove(soHeader);
        return Results.success("200");
    }


    /**
     * 多条件查询业务模块
     *
     * @param soHeaderParam
     * @param pageRequest
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单汇总查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/order/querytotal")
    public ResponseEntity<Page<SoHeaderDTO>> queryByOrderTotal(
            SoHeaderParam soHeaderParam,
            PageRequest pageRequest,
            @PathVariable("organizationId") Long organizationId) {
        Page<SoHeaderDTO> list = soHeaderService.queryByOrderTotal(pageRequest, soHeaderParam);
        return Results.success(list);
    }

    /**
     * 根据订单头查询API
     *
     * @param organizationId
     * @param soHeaderId
     * @return
     */
    @ApiOperation(value = "根据订单头查询")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/order/{soHeaderId}")
    public ResponseEntity<SoHeaderDTO> queryById(@PathVariable("organizationId") Long organizationId, @PathVariable Long soHeaderId) {
        SoHeaderDTO soHeaderDTO = soHeaderService.queryBySoHeaderId(organizationId, soHeaderId);
        return Results.success(soHeaderDTO);
    }

    /**
     * 订单状态API
     *
     * @param
     * @param orderStatus
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单状态Api")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("order/status/updateOrder")
    public ResponseEntity<?> orderStatus(long soHeaderId, String orderStatus,
                                         @PathVariable Long organizationId) {
        soHeaderService.updateByStatus(soHeaderId, orderStatus);
        return Results.success();
    }

    /**
     * 订单头保存API
     *
     * @param soOrderVO
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单保存API")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/save-order")
    public ResponseEntity<SoOrderVO> saveOrder(@RequestBody SoOrderVO soOrderVO, @PathVariable("organizationId") Long organizationId) {
        SoOrderVO soOrderVoNew = soHeaderService.saveOrder(soOrderVO);
        return Results.success(soOrderVoNew);
    }

    /**
     * 根据订单头删除订单信息
     *
     * @param soHeader
     * @param organizationId
     * @return
     */
    @ApiOperation(value = "订单删除")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @DeleteMapping("/order-delete")
    public ResponseEntity<?> deleteByOrderId(@RequestBody SoHeader soHeader, @PathVariable String organizationId) {
        soHeaderService.deleteByOrderId(soHeader);
        return Results.success("200");
    }

    /**
     * @param organizationId    租户ID
     *  soHeaderId     订单头id
     * @return
     */
    @ApiOperation(value = "启动审批流")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/workflow/{soHeaderId}")
    public ResponseEntity<?> orderStatusWorkflow(@PathVariable long organizationId,@PathVariable("soHeaderId") long soHeaderId)
        throws CheckedException {
        soHeaderService.orderStatusWorkflow(organizationId,soHeaderId);
        return Results.success();
    }


    /**
     * 审批流结束后修改订单状态
     *
     * @param organizationId 租户id
     * @param soHeaderId 订单头id
     * @param status  修改后的状态
     * @return
     * @throws CheckedException   状态校验异常
     */
    @ApiOperation(value = "审批结束后修改状态")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/workflowChangeStatus/{soHeaderId}/{status}")
    public ResponseEntity<?> workflowChangeStatus(
            @PathVariable("organizationId") Long organizationId,
            @PathVariable("soHeaderId") Long soHeaderId,
            @PathVariable("status") String status) throws CheckedException {
        soHeaderService.workflowChangeStatus(organizationId, soHeaderId,status);
        return Results.success();
    }


}
