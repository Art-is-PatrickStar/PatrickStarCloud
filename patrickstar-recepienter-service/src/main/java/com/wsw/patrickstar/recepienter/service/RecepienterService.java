package com.wsw.patrickstar.recepienter.service;


import com.wsw.patrickstar.recepienter.entity.Recepienter;

/**
 * @Author WangSongWen
 * @Date: Created in 13:44 2020/11/20
 * @Description:
 */
public interface RecepienterService {
    /**
     * 新增领取者
     *
     * @param recepienter
     * @return
     */
    int create(Recepienter recepienter);
}
