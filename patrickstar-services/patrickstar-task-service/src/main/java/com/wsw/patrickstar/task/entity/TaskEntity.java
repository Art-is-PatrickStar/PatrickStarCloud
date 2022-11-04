package com.wsw.patrickstar.task.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author WangSongWen
 * @Date: Created in 13:28 2020/11/9
 * @Description: 任务实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("task")
@ApiModel("任务信息实体")
public class TaskEntity {
    @TableId(value = "task_id", type = IdType.INPUT)
    @ApiModelProperty(value = "任务唯一性ID")
    private Long taskId;

    @TableField
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @TableField
    @ApiModelProperty(value = "任务描述")
    private String taskCaption;

    @TableField
    @ApiModelProperty(value = "扩展字段")
    private String extend;

    @TableField
    @ApiModelProperty(value = "创建人员")
    private String createUser;

    @TableField
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField
    @ApiModelProperty(value = "更新人员")
    private String updateUser;

    @TableField
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
