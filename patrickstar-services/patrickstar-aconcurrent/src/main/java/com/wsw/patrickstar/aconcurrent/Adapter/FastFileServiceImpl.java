package com.wsw.patrickstar.aconcurrent.Adapter;

import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 10:57 2021/4/16
 * @Description:
 */
@Service("fastFileService")
public class FastFileServiceImpl implements FileService {
    @Override
    public void uploadFile(String fileName) {
        System.out.println("FastFileService uploadFile " + fileName + "...");
    }

    @Override
    public String downloadFile(String fileName) {
        System.out.println("FastFileService downloadFile " + fileName + "...");
        return null;
    }
}
