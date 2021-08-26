package com.wsw.patrickstar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Author WangSongWen
 * @Date: Created in 13:28 2020/11/9
 * @Description: 任务实体类
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "task", type = "_doc")
@TableName("task")
public class Task implements Serializable {
    private static final long serialVersionUID = 7690326552082622459L;

    @Id
    @TableId(type = IdType.AUTO)
    private Long taskId;  // 任务唯一性ID

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @TableField
    private String taskName;  // 任务名称

    @TableField
    private String taskCaption;  // 任务描述

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createDate;  // 创建日期

    @TableField
    private char taskStatus;  // 任务状态 0-创建 1-被领取 2-待测试 3-测试完成 4-已归档

    @TableField
    private Long recepientId;  // 任务领取者id

    @TableField
    private String recepientName;  // 任务领取者名称

    @TableField
    private Long testerId;  // 任务测试者id

    @TableField
    private String testerName;  // 任务测试者名称

    @TableField
    private char archive;  // 是否归档，0-未归档 1-已归档

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date modifyDate;  // 修改日期

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Task))
            return false;
        Task task = (Task) obj;
        return Objects.equals(getTaskId(), task.getTaskId()) &&
                Objects.equals(getTaskName(), task.getTaskName()) &&
                Objects.equals(getTaskCaption(), task.getTaskCaption()) &&
                Objects.equals(getCreateDate(), task.getCreateDate()) &&
                Objects.equals(getTaskStatus(), task.getTaskStatus()) &&
                Objects.equals(getRecepientId(), task.getRecepientId()) &&
                Objects.equals(getRecepientName(), task.getRecepientName()) &&
                Objects.equals(getTesterId(), task.getTaskId()) &&
                Objects.equals(getTesterName(), task.getTesterName()) &&
                Objects.equals(getArchive(), task.getArchive()) &&
                Objects.equals(getModifyDate(), task.getModifyDate());
    }
}
