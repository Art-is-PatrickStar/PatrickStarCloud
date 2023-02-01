package com.wsw.patrickstar.aconcurrent.fastdfs.service;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.exception.FdfsException;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.wsw.patrickstar.aconcurrent.fastdfs.domain.FastFile;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 16:59 2020/12/28
 * @Description:
 */
@Service
public class FastDfsServiceImpl implements FastDfsService {
    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public FastFile uploadFile(FastFile fastFile) throws Exception {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(fastFile.getFileContent(), fastFile.getFileExtName());
            return new FastFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败!", e);
        }
    }

    @Override
    @Retryable(value = {FdfsException.class}, maxAttempts = 2)
    public byte[] downloadFile(String groupName, String filePath) {
        return fastFileStorageClient.downloadFile(groupName, filePath);
    }


}
