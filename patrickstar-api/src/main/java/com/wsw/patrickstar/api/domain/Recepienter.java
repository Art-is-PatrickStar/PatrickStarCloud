package com.wsw.patrickstar.api.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recepienter implements Serializable {
    private static final long serialVersionUID = -8010801768253092925L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String remark;
}