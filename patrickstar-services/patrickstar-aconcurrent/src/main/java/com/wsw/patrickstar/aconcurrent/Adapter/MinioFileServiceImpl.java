package com.wsw.patrickstar.aconcurrent.Adapter;

import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 11:00 2021/4/16
 * @Description:
 */
@Service("minioFileService")
public class MinioFileServiceImpl implements MinioFileService {
    @Override
    public void uploadFile(String fileName) {
        System.out.println("MinioFileService uploadFile " + fileName + "...");
    }

    @Override
    public String downloadFile(String fileName) {
        System.out.println("MinioFileService downloadFile " + fileName + "...");
        return null;
    }

    @Override
    public boolean checkFile(String filename) {
        return false;
    }
}
