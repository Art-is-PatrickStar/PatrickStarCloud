package com.wsw.patrickstar.aconcurrent.Adapter;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 11:01 2021/4/16
 * @Description:
 */
@Service("fileService")
public class FileServiceAdapterImpl implements FileService {
    @Resource
    private FileService fastFileService;
    @Resource
    private FileService minioFileService;

    @Override
    public void uploadFile(String fileName) {
        fastFileService.uploadFile(fileName);
        minioFileService.uploadFile(fileName);
    }

    @Override
    public String downloadFile(String fileName) {
        String file = fastFileService.downloadFile(fileName);
        if (file == null)
            file = minioFileService.downloadFile(fileName);
        return file;
    }
}
