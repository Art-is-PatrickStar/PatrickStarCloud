package com.wsw.patrickstar.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/7 16:03
 */
@Data
@ApiModel("认证服务用户响应参数")
public class UserAuthResponseDTO implements Serializable {
    private static final long serialVersionUID = 5528539076224294972L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "refreshToken")
    private String refreshToken;
}
