package com.wsw.patrickstar.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 17:42 2020/11/12
 * @Description:
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -5041579146782385968L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @TableField("username_chn")
    private String usernameCHN;
}
