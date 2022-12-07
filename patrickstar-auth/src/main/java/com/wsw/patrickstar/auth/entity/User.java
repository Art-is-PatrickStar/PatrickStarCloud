package com.wsw.patrickstar.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author WangSongWen
 * @Date: Created in 17:42 2020/11/12
 * @Description:
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息实体")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @Column(nullable = false)
    @ApiModelProperty(value = "用户名")
    private String username;

    @Column(nullable = false)
    @ApiModelProperty(value = "密码")
    private String password;

    @Column
    @ApiModelProperty(value = "邮箱")
    private String mail;

    @Column
    @ApiModelProperty(value = "其他")
    private String extend;
}
