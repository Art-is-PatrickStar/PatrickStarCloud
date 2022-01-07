package com.wsw.patrickstar.task.Schedule.executor;

import com.wsw.patrickstar.task.Schedule.job.TaskJob;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: wangsongwen
 * @Date: 2021/9/30 16:46
 * @Description: xxl-job: taskJobHandler
 */
@Component
public class TaskJobExecutor {
    @Resource
    private TaskJob taskJob;

    @XxlJob("taskJobHandler")
    public void taskJobHandler() throws Exception {
        String param = XxlJobHelper.getJobParam();
        taskJob.execute(param);
    }
}
