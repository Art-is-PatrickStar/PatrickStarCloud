package com.wsw.patrickstar.kafka.service.entity;

import com.wsw.patrickstar.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 10:02
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
