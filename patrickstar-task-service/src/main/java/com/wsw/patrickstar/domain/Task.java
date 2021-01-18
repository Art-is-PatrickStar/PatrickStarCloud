package com.wsw.summercloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 13:28 2020/11/9
 * @Description: 任务实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private Long taskId;  // 任务唯一性ID
    private String taskName;  // 任务名称
    private String taskCaption;  // 任务描述
    private Integer createDate;  // 创建日期
    private char taskStatus;  // 任务状态 0-创建 1-被领取 2-待测试 3-测试完成 4-已归档
    private Long recepientId;  // 任务领取者id
    private String recepientName;  // 任务领取者名称
    private Long testerId;  // 任务测试者id
    private String testerName;  // 任务测试者名称
    private char archive;  // 是否归档，0-未归档 1-已归档
    private Integer modifyDate;  // 修改日期
}
