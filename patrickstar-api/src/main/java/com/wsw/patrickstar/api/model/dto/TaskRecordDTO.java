package com.wsw.patrickstar.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/18 13:38
 */
@Data
@Builder
@ApiModel("任务记录")
public class TaskRecordDTO implements Serializable {
    private static final long serialVersionUID = 3539263464691299717L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "任务唯一性ID")
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    @ApiModelProperty(value = "任务类型 1-生产 2-测试 3-稽核")
    private Integer taskType;

    @ApiModelProperty(value = "任务状态 1-待处理 2-处理中 3-处理完成")
    private Integer taskStatus;

    @ApiModelProperty(value = "扩展字段")
    private String extend;

    @ApiModelProperty(value = "创建人员")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人员")
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
