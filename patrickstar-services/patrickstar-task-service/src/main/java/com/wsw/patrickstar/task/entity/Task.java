package com.wsw.patrickstar.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Objects;

/**
 * @Author WangSongWen
 * @Date: Created in 13:28 2020/11/9
 * @Description: 任务实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "task", type = "_doc")
@TableName("task")
public class Task {
    @Id
    @TableId(type = IdType.AUTO)
    private Long id; // 主键

    @TableField
    private Long taskId;  // 任务唯一性ID

    @TableField
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String taskName;  // 任务名称

    @TableField
    private String taskCaption;  // 任务描述

    @TableField
    private String extend;  // 扩展字段

    @TableField
    private String createUser;  // 创建人员

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;  // 创建时间

    @TableField
    private String updateUser;  // 更新人员

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;  // 更新时间

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Task))
            return false;
        Task task = (Task) obj;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getTaskId(), task.getTaskId()) &&
                Objects.equals(getTaskName(), task.getTaskName()) &&
                Objects.equals(getTaskCaption(), task.getTaskCaption()) &&
                Objects.equals(getExtend(), task.getExtend()) &&
                Objects.equals(getCreateUser(), task.getCreateUser()) &&
                Objects.equals(getCreateTime(), task.getCreateTime()) &&
                Objects.equals(getUpdateUser(), task.getUpdateUser()) &&
                Objects.equals(getUpdateTime(), task.getUpdateTime());
    }
}
