package com.wsw.patrickstar.recepienter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recepienter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Integer taskType;  // 任务类型 1-生产 2-测试 3-稽核

    @Column(nullable = false)
    private Integer taskStatus;  // 任务状态 1-待处理 2-处理中 3-处理完成

    @Column(nullable = false)
    private String createUser;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private String updateUser;

    @Column(nullable = false)
    private Date updateTime;

    @Column(nullable = false)
    private String extend;
}