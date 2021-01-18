package com.wsw.patrickstarrecepienterservice.controller;

import com.wsw.patrickstarrecepienterservice.service.RecepienterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 14:30 2020/11/20
 * @Description:
 */
@RestController
public class RecepienterController {
    @Resource
    private RecepienterService recepienterService;

    @PostMapping("/recepienter/create")
    public int create(@RequestParam("taskId") Long taskId, @RequestParam("taskName") String taskName, @RequestParam("name") String name, @RequestParam("remark") String remark){
        return recepienterService.insert(taskId, taskName, name, remark);
    }
}
