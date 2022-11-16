package com.wsw.patrickstar.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/7/19 10:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("operation_log")
@ApiModel("日志信息实体")
public class OperationLogEntity {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField
    @ApiModelProperty(value = "模块名")
    private String moduleType;

    @TableField
    @ApiModelProperty(value = "模块id")
    private String moduleId;

    @TableField
    @ApiModelProperty(value = "操作类型")
    private String operateType;

    @TableField
    @ApiModelProperty(value = "操作内容")
    private String operateContent;

    @TableField
    @ApiModelProperty(value = "创建人员")
    private String createdUser;

    @TableField
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
