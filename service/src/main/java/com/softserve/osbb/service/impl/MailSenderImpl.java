package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Mail;
import com.softserve.osbb.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Anastasiia Fedorak on 8/13/16.
 */

@Service
public class MailSenderImpl implements MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    private MimeMessage message;
    private MimeMessageHelper helper;

    public void send(String to, String subject, String text) throws MessagingException {
        message = javaMailSender.createMimeMessage();
        helper = new MimeMessageHelper(message, true); //true for multipart
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(text, true); //true for html

        javaMailSender.send(message);
    }

    public void send(Mail mail) throws MessagingException {
        message = javaMailSender.createMimeMessage();
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getText());
        helper.setTo(mail.getTo());
    }

}
