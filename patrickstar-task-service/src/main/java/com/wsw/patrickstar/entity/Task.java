package com.wsw.patrickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 13:28 2020/11/9
 * @Description: 任务实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;  // 任务唯一性ID

    @Column(nullable = false)
    private String taskName;  // 任务名称

    @Column(nullable = false)
    private String taskCaption;  // 任务描述

    @Column(nullable = false)
    private Integer createDate;  // 创建日期

    @Column(nullable = false)
    private char taskStatus;  // 任务状态 0-创建 1-被领取 2-待测试 3-测试完成 4-已归档

    @Column(nullable = false)
    private Long recepientId;  // 任务领取者id

    @Column(nullable = false)
    private String recepientName;  // 任务领取者名称

    @Column(nullable = false)
    private Long testerId;  // 任务测试者id

    @Column(nullable = false)
    private String testerName;  // 任务测试者名称

    @Column(nullable = false)
    private char archive;  // 是否归档，0-未归档 1-已归档

    @Column(nullable = false)
    private Integer modifyDate;  // 修改日期
}
