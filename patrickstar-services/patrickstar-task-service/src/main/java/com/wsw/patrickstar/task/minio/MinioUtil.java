//package com.wsw.patrickstar.task.minio;
//
//import com.wsw.patrickstar.task.config.MinioConfig;
//import io.minio.MinioClient;
//import io.minio.ObjectStat;
//import io.minio.PutObjectOptions;
//import io.minio.messages.Bucket;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.List;
//
///**
// * @Author WangSongWen
// * @Date: Created in 16:38 2021/3/3
// * @Description:
// */
//@Slf4j
//@Component
//public class MinioUtil {
//    @Resource
//    private MinioConfig minioConfig;
//
//    private MinioClient minioClient;
//
//    @PostConstruct
//    public void init() {
//        try {
//            minioClient = new MinioClient(minioConfig.getUrl(), minioConfig.getAccessKey(), minioConfig.getSecretKey());
//            createBucket(minioConfig.getBucketName());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("初始化minio配置异常: " + e.fillInStackTrace());
//        }
//    }
//
//    //判断 bucket是否存在
//    @SneakyThrows(Exception.class)
//    public boolean bucketExists(String bucketName) {
//        return minioClient.bucketExists(bucketName);
//    }
//
//    /**
//     * 创建 bucket
//     */
//    @SneakyThrows(Exception.class)
//    public void createBucket(String bucketName) {
//        boolean isExist = minioClient.bucketExists(bucketName);
//        if (!isExist) {
//            minioClient.makeBucket(bucketName);
//        }
//    }
//
//    /**
//     * 获取全部bucket
//     */
//    @SneakyThrows(Exception.class)
//    public List<Bucket> getAllBuckets() {
//        return minioClient.listBuckets();
//    }
//
//    /**
//     * 文件上传
//     */
//    @SneakyThrows(Exception.class)
//    public void upload(String bucketName, String fileName, String filePath) {
//        minioClient.putObject(bucketName, fileName, filePath, null);
//    }
//
//    /**
//     * 文件上传
//     */
//    @SneakyThrows(Exception.class)
//    public String upload(String bucketName, String fileName, InputStream stream) {
//        minioClient.putObject(bucketName, fileName, stream, new PutObjectOptions(stream.available(), -1));
//        return getFileUrl(bucketName, fileName);
//    }
//
//    /**
//     * 文件上传
//     */
//    @SneakyThrows(Exception.class)
//    public String upload(String bucketName, MultipartFile file) {
//        final InputStream is = file.getInputStream();
//        final String fileName = file.getOriginalFilename();
//        minioClient.putObject(bucketName, fileName, is, new PutObjectOptions(is.available(), -1));
//        is.close();
//        return getFileUrl(bucketName, fileName);
//    }
//
//    /**
//     * 删除文件
//     */
//    @SneakyThrows(Exception.class)
//    public void deleteFile(String bucketName, String fileName) {
//        minioClient.removeObject(bucketName, fileName);
//    }
//
//    /**
//     * 下载文件
//     */
//    @SneakyThrows(Exception.class)
//    public void download(String bucketName, String fileName, HttpServletResponse response) {
//        // 获取对象的元数据
//        final ObjectStat stat = minioClient.statObject(bucketName, fileName);
//        response.setContentType(stat.contentType());
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//        InputStream is = minioClient.getObject(bucketName, fileName);
//        IOUtils.copy(is, response.getOutputStream());
//        is.close();
//    }
//
//    @SneakyThrows(Exception.class)
//    public void download(String bucketName, String fileName) {
//        final ObjectStat stat = minioClient.statObject(bucketName, fileName);
//        System.out.println(stat.toString());
//    }
//
//    /**
//     * 获取minio文件的下载地址
//     */
//    @SneakyThrows(Exception.class)
//    public String getFileUrl(String bucketName, String fileName) {
//        return minioClient.presignedGetObject(bucketName, fileName);
//    }
//}
