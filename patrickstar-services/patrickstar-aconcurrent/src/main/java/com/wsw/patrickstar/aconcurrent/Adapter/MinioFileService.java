package com.wsw.patrickstar.aconcurrent.Adapter;

/**
 * @Author WangSongWen
 * @Date: Created in 10:56 2021/4/16
 * @Description:
 */
public interface MinioFileService extends FileService {
    boolean checkFile(String filename);
}
