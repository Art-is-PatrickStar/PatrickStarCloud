package com.wsw.patrickstar.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/7 15:40
 */
@Data
@ApiModel("用户登录请求参数")
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = -495679591471260542L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 50)
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 1024)
    private String password;
}
