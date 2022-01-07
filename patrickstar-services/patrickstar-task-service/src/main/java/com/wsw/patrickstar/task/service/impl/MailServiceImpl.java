package com.wsw.patrickstar.task.service.impl;

import com.wsw.patrickstar.common.exception.CloudServiceException;
import com.wsw.patrickstar.task.service.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 13:14 2021/3/23
 * @Description:
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(SimpleMailMessage simpleMailMessage) throws CloudServiceException {
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (CloudServiceException e) {
            throw new CloudServiceException("发送邮件异常: " + e.getMessage());
        }
    }
}
