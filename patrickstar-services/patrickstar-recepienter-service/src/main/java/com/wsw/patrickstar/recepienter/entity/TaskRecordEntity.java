package com.wsw.patrickstar.recepienter.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task_record")
@ApiModel("任务记录实体")
public class TaskRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(nullable = false)
    @ApiModelProperty(value = "任务唯一性ID")
    private Long taskId;

    @Column(nullable = false)
    @ApiModelProperty(value = "任务类型 1-生产 2-测试 3-稽核")
    private Integer taskType;

    @Column(nullable = false)
    @ApiModelProperty(value = "任务状态 1-待处理 2-处理中 3-处理完成")
    private Integer taskStatus;

    @Column(nullable = false)
    @ApiModelProperty(value = "扩展字段")
    private String extend;

    @Column(nullable = false)
    @ApiModelProperty(value = "创建人员")
    private String createUser;

    @Column(nullable = false)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(nullable = false)
    @ApiModelProperty(value = "更新人员")
    private String updateUser;

    @Column(nullable = false)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}