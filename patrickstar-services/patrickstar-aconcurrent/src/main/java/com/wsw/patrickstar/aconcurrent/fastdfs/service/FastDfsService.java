package com.wsw.patrickstar.aconcurrent.fastdfs.service;

import com.wsw.patrickstar.aconcurrent.fastdfs.domain.FastFile;

/**
 * @Author WangSongWen
 * @Date: Created in 17:02 2020/12/28
 * @Description:
 */
public interface FastDfsService {
    FastFile uploadFile(FastFile fastFile) throws Exception;

    byte[] downloadFile(String groupName, String filePath);
}
