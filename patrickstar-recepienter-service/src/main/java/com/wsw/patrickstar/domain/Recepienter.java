package com.wsw.patrickstar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepienter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long taskId;
    private String taskName;
    private String name;
    private String remark;
}