package com.wsw.patrickstar.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: wangsongwen
 * @Date: 2022/7/18 13:44
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -495679591471260542L;

    private Long id;

    private Long userId;

    private String username;

    private String password;
}
