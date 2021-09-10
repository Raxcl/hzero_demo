package org.hand.order.infra.handler;

import org.hand.order.api.controller.v1.OrderMessageController;
import org.hand.order.api.controller.v1.SoHeaderController;
import org.hand.order.domain.entity.dto.SoHeaderDTO;
import org.hand.order.infra.mapper.SoHeaderMapper;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 33265
 */
@JobHandler("HandSchedulerDemoHandler")
public class HandSchedulerDemoHandler implements IJobHandler {
    @Resource
    private SoHeaderMapper soHeaderMapper;

    @Resource
    private OrderMessageController orderMessageController;

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {

        //每天凌晨3点 ，定时将销售订单状态为已审批的修改为已关闭
        soHeaderMapper.updateByTime();
        orderMessageController.hello("消息发送成功");
        tool.info("任务执行完毕了...");
        tool.info("参数 = " + map.toString());
        return ReturnT.SUCCESS;
    }

    @Override
    public void onCreate(SchedulerTool tool) {
        tool.info("任务执行前置onCreate...");
    }

    @Override
    public void onException(SchedulerTool tool) {
        tool.info("任务执行错误onException...");
    }

    @Override
    public void onFinish(SchedulerTool tool, ReturnT returnT) {
        tool.info("任务执行完毕onFinish...");
    }
}
