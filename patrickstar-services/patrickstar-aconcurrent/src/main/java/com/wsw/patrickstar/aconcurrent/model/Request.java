package com.wsw.patrickstar.aconcurrent.model;

import com.wsw.patrickstar.aconcurrent.annotation.SensitiveInfo;
import com.wsw.patrickstar.aconcurrent.enums.SensitiveType;
import lombok.Data;

/**
 * @Author WangSongWen
 * @Date: Created in 14:25 2021/3/15
 * @Description:
 */
@Data
public class Request {
    @SensitiveInfo(type = SensitiveType.NAME)
    private String name;
    @SensitiveInfo(type = SensitiveType.ID_CARD)
    private String idCard;
    @SensitiveInfo(type = SensitiveType.PHONE)
    private String phone;
    @SensitiveInfo(type = SensitiveType.IMG)
    private String img;
}
