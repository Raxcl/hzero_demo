package org.hand.training.infra.handler;

import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;

import java.util.Map;

@JobHandler("HandSchedulerDemoHandler")
public class HandSchedulerDemoHandler implements IJobHandler {
    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        tool.updateProgress(1, "任务开始...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tool.updateProgress(50, "任务执行50%...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tool.updateProgress(100, "任务执行完毕...");
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
