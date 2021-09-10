/*
package org.hand.order.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceResponse;
import org.hand.order.app.service.SoHeaderService;
import org.hand.order.config.SwaggerTags;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.core.base.BaseController;
import org.hzero.core.exception.CheckedException;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags = SwaggerTags.HAND_WORKFLOW)
@RestController("handWorkflowController.v1")
@RequestMapping("/v1/{organizationId}/workflow")
public class OrderWorkflowController extends BaseController {

    @Resource
    private SoHeaderService soHeaderService;

    @ApiOperation(value = "启动审批流")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PutMapping("/workflow/{soHeaderId}")
    public ResponseEntity<?> orderStatusWorkflow(@PathVariable Long soHeaderId,@PathVariable("organizationId") Long organizationId)
        throws CheckedException {
        //soHeaderService.orderStatusWorkflow(organizationId,soHeaderId);
        return Results.success("qwq");
    }



}
*/
