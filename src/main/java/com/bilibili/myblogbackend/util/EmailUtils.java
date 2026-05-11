/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.exception.EmailFormatException
 *  com.bilibili.myblogbackend.util.EmailUtils
 *  jakarta.mail.MessagingException
 *  jakarta.mail.internet.MimeMessage
 *  lombok.Generated
 *  org.springframework.mail.javamail.JavaMailSender
 *  org.springframework.mail.javamail.MimeMessageHelper
 *  org.springframework.stereotype.Component
 */
package com.bilibili.myblogbackend.util;

import com.bilibili.myblogbackend.exception.EmailFormatException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import lombok.Generated;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    private final JavaMailSender javaMailSender;
    private final Random rand = new Random();

    public String sendMessage(String email) throws MessagingException, UnsupportedEncodingException {
        if (!email.matches("^[0-9]+@qq.com$")) {
            throw new EmailFormatException("请输入正确的邮箱格式");
        } else {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom("3388659470@qq.com", "验证码");
            helper.setTo(email);
            helper.setSubject("邮箱验证码");
            String result = String.valueOf(this.rand.nextInt(10000, 99999));
            helper.setText(result, false);
            this.javaMailSender.send(message);
            return result;
        }
    }

    @Generated
    public EmailUtils(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}

