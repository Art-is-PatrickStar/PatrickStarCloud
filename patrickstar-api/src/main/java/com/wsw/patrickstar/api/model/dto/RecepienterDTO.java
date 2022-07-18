package com.wsw.patrickstar.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: wangsongwen
 * @Date: 2022/7/18 13:38
 */
@Data
public class RecepienterDTO implements Serializable {
    private static final long serialVersionUID = 3539263464691299717L;

    private Long id;

    private Long taskId;

    private Integer taskType;

    private Integer taskStatus;

    private String createUser;

    private String createTime;

    private String updateUser;

    private String updateTime;

    private String extend;
}
