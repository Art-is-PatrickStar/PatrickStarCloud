package com.wsw.patrickstar.aconcurrent.pdf;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author WangSongWen
 * @Date: Created in 10:43 2021/4/27
 * @Description:
 */
public class StringReplaceUtil {
    /**
     * @Title: replaceUTFSpace
     * @Description: 替换UTF-8编码格式中编码为“0xC2 0xA0”的特殊空格，该空格入库数据库之后表现为“?”
     * @param content
     * @return
     * @author jianghw
     * @date 2020年11月2日 下午5:08:19
     */
    public static String replaceUTFSpace(String content) {
        try {
            byte[] space = new byte[] {(byte) 0xC2, (byte) 0xA0};
            String UTFSpace = new String(space, StandardCharsets.UTF_8);
            return StringUtils.replace(content, UTFSpace, " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * @desc: 特殊字符处理
     * @param content
     * @return String
     * @author wangsw
     * @date 2021年4月25日 上午10:50:54
     */
    public static String replaceSpecialCharacters(String content) {
        try {
            // 1. 文本中字符 "•"与"·"的替换
            content = StringUtils.replace(content, "•", "·");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
