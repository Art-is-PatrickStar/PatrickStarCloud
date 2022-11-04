package com.wsw.patrickstar.api.model.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 分页参数
 * @Author: wangsongwen
 * @Date: 2022/7/19 16:33
 */
@Data
public class PageParam {
    @ApiModelProperty(value = "页码", required = true)
    private Integer start;

    @ApiModelProperty(value = "每页数量", required = true)
    private Integer length;
}
