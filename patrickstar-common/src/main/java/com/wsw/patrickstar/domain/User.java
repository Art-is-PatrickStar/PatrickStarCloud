package com.wsw.patrickstar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author WangSongWen
 * @Date: Created in 17:42 2020/11/12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long uid;
    private String username;
    private String password;
    private String usernameCHN;
}
