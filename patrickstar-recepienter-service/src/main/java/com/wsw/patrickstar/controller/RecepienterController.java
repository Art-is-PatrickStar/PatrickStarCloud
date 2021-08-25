package com.wsw.patrickstar.controller;

import com.wsw.patrickstar.entity.Recepienter;
import com.wsw.patrickstar.service.RecepienterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public int create(@RequestBody Recepienter recepienter) {
        return recepienterService.create(recepienter);
    }
}
