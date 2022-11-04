package com.wsw.patrickstar.task.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @Author WangSongWen
 * @Date: Created in 11:14 2021/3/23
 * @Description:
 */
public interface MailService {
    void sendMail(SimpleMailMessage simpleMailMessage) throws Exception;
}
