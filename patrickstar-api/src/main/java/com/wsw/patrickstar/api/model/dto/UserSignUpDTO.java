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
 * @Date: 2022/12/7 15:51
 */
@Data
@ApiModel("用户注册请求参数")
public class UserSignUpDTO implements Serializable {
    private static final long serialVersionUID = -9127441896999545262L;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 50)
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 1024)
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "其他")
    private String extend;
}
