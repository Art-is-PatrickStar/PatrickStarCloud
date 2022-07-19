package com.wsw.patrickstar.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: wangsongwen
 * @Date: 2022/7/18 13:39
 */
@Data
@ApiModel("任务信息")
public class TaskDTO implements Serializable {
    private static final long serialVersionUID = -3114148300594531891L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "任务唯一性ID")
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务描述")
    private String taskCaption;

    @ApiModelProperty(value = "扩展字段")
    private String extend;

    @ApiModelProperty(value = "创建人员")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新人员")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;
}
