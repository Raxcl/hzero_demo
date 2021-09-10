/*
package org.hand.order.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hand.order.config.SwaggerTags;
import org.hand.order.domain.entity.SoHeader;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hzero.boot.interfaces.sdk.dto.RequestPayloadDTO;
import org.hzero.boot.interfaces.sdk.invoke.InterfaceInvokeSdk;
import org.hzero.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

*/
/**
 * 订单报表接口
 *//*

@Api(tags = SwaggerTags.HAND_REPORT)
@RestController("handInterfaceController.v1")
@RequestMapping("/v1/{organizationId}/hand/interface")
public class OrderReportController extends BaseController {

    @ApiOperation(value = "订单报表客户端")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/order-report")
    public ResponseEntity<Page<SoHeader>> orderReport(@PathVariable long organizationId, SoHeader soHeader,
                                                      @ApiIgnore PageRequest pageRequest) {
        String namespace = "HZERO";
        String interfaceCode = "ORDER_DEMO_037_1";
        String serverCode = "ORDER_DEMO_037";
        RequestPayloadDTO payload = new RequestPayloadDTO();
        ResponseEntity<SoHeaderDTO> soHeaderDTOResponseEntity = soHeaderController.queryById(0L, soHeaderId);

        return soHeaderDTOResponseEntity;
    }
}
*/
