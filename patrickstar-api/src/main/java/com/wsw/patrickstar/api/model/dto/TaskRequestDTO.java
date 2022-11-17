package com.wsw.patrickstar.api.model.dto;

import com.wsw.patrickstar.api.model.dto.base.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 16:28
 */
@Data
@ApiModel("任务查询参数")
@EqualsAndHashCode(callSuper = true)
public class TaskRequestDTO extends PageParam implements Serializable {
    private static final long serialVersionUID = -9175721821194835689L;

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

    @ApiModelProperty(value = "更新人员")
    private String updateUser;

    @ApiModelProperty(value = "创建时间-开始")
    private Date createTimeStart;

    @ApiModelProperty(value = "创建时间-结束")
    private Date createTimeEnd;

    @ApiModelProperty(value = "更新时间-开始")
    private Date updateTimeStart;

    @ApiModelProperty(value = "更新时间-结束")
    private Date updateTimeEnd;
}
