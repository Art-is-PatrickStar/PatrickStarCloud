package com.wsw.patrickstar.aconcurrent.fastdfs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 9:18 2020/12/29
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastFile implements Serializable {
    private String fileName;
    private byte[] fileContent;
    private String fileExtName;
    private String hashCode;
    private String groupName;
    private String filePath;

    public FastFile(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileExtName = FilenameUtils.getExtension(fileName);
    }

    public FastFile(String groupName, String filePath) {
        this.groupName = groupName;
        this.filePath = filePath;
    }
}
