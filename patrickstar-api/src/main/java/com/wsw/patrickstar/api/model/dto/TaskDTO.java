package com.wsw.patrickstar.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: wangsongwen
 * @Date: 2022/7/18 13:39
 */
@Data
public class TaskDTO implements Serializable {
    private static final long serialVersionUID = -3114148300594531891L;

    private Long id;

    private Long taskId;

    private String taskName;

    private String taskCaption;

    private String extend;

    private String createUser;

    private String createTime;

    private String updateUser;

    private String updateTime;
}
