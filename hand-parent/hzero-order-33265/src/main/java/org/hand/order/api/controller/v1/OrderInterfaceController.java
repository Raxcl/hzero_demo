package org.hand.order.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hand.order.config.SwaggerTags;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hzero.boot.interfaces.sdk.dto.RequestPayloadDTO;
import org.hzero.boot.interfaces.sdk.dto.ResponsePayloadDTO;
import org.hzero.boot.interfaces.sdk.invoke.InterfaceInvokeSdk;
import org.hzero.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * API接口
 * @author 33265
 */
@Api(tags = SwaggerTags.HAND_INTERFACE)
@RestController("handInterfaceController.v1")
@RequestMapping("/v1/{organizationId}/hand/interface")
public class OrderInterfaceController extends BaseController {
    @Autowired
    private InterfaceInvokeSdk interfaceInvokeSdk;
    @Resource
    private SoHeaderController soHeaderController;

    @ApiOperation(value = "调用一个接口")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/invoke/{soHeaderId}")
    public ResponseEntity<SoHeaderDTO> invoke(@PathVariable long organizationId,@PathVariable Long soHeaderId) {
        String namespace = "HZERO";
        String interfaceCode = "ORDER_DEMO_037_1";
        String serverCode = "ORDER_DEMO_037";
        RequestPayloadDTO payload = new RequestPayloadDTO();
        ResponseEntity<SoHeaderDTO> soHeaderDtoResponseEntity = soHeaderController.queryById(0L, soHeaderId);

        return soHeaderDtoResponseEntity;
    }
}
