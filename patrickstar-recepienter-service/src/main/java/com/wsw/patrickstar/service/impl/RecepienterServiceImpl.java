package com.wsw.patrickstar.service.impl;

import com.wsw.patrickstar.entity.Recepienter;
import com.wsw.patrickstar.repository.RecepienterRepository;
import com.wsw.patrickstar.service.RecepienterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Slf4j
@Service
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterRepository recepienterRepository;

    @Override
    public int create(Recepienter recepienter) {
        try {
            recepienterRepository.save(recepienter);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增领取者异常: " + e.getMessage());
            return 0;
        }
        return 1;
    }
}
