package org.hand.training.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceResponse;
import org.hand.training.config.SwaggerTags;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.entity.ProcessInstanceHistory;
import org.hzero.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Api(tags = SwaggerTags.HAND_WORKFLOW_PLUS)
@RestController("handWorkflowPlusController.v1")
@RequestMapping("/v1/{organizationId}/hand/workflow/plus")
public class HandWorkflowPlusController extends BaseController {
    @Autowired
    private WorkflowClient workflowClient;

    @ApiOperation(value = "启动一个流程")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @PostMapping("/start")
    public ResponseEntity<ProcessInstanceResponse> startUp(@PathVariable long organizationId, String employeeNum, String businessKey, String ProcessDefinitionKey) {
        ProcessInstanceCreateRequest request = new ProcessInstanceCreateRequest();
        request.setBusinessKey(businessKey);
        request.setProcessDefinitionKey(ProcessDefinitionKey);
        return workflowClient.startUp(organizationId, employeeNum, request);
    }

    @ApiOperation(value = "查询流程信息")
    @Permission(level = ResourceLevel.ORGANIZATION)
    @GetMapping("/query")
    public ResponseEntity<List<ProcessInstanceHistory>> listTaskHistory(@PathVariable long organizationId, String businessKey, String businessKeyLike) {
        return workflowClient.listTaskHistory(organizationId, businessKey, businessKeyLike);
    }
}
