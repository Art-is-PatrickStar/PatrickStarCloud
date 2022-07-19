package com.wsw.patrickstar.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:38
 */
@Data
@ApiModel("日志信息")
public class OpLogDTO implements Serializable {
    private static final long serialVersionUID = 2530679455618227418L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "模块名")
    private String moduleType;

    @ApiModelProperty(value = "模块id")
    private String moduleId;

    @ApiModelProperty(value = "操作类型")
    private String operateType;

    @ApiModelProperty(value = "操作内容")
    private String operateContent;

    @ApiModelProperty(value = "创建人员")
    private String createdUser;

    @ApiModelProperty(value = "创建时间")
    private String createdTime;
}
