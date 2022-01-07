package com.wsw.patrickstar.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/17 9:55
 * @Description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskMessage extends BaseEntity {
    private static final long serialVersionUID = -5572153467631898404L;

    private Long taskId;

    private String message;
}
