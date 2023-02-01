package com.wsw.patrickstar.aconcurrent.Adapter;

/**
 * @Author WangSongWen
 * @Date: Created in 10:53 2021/4/16
 * @Description:
 */
public interface FileService {
    void uploadFile(String fileName);

    String downloadFile(String fileName);
}
