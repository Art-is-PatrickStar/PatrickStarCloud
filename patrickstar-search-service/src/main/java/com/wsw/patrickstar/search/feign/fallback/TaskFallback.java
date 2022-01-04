package com.wsw.patrickstar.search.feign.fallback;

import com.wsw.patrickstar.search.feign.client.TaskClient;
import feign.hystrix.FallbackFactory;

/**
 * @Author WangSongWen
 * @Date: Created in 15:02 2021/1/18
 * @Description:
 */
public class TaskFallback implements FallbackFactory<TaskClient> {

    @Override
    public TaskClient create(Throwable throwable) {
        return null;
    }
}
