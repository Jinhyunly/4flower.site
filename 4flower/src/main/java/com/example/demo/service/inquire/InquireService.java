package com.example.demo.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.email.Mail;

@Service
public class InquireService {
	@Autowired
  public JavaMailSender emailSender;

	public void sendSimpleMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getMailTo());
//        message.setFrom(mail.getMailFrom());
        message.setSubject(mail.getMailSubject());
        message.setText("From Name : " + mail.getFromName() + "\nFrom MailAddress : " + mail.getMailFrom() + "\nphone Number : " + mail.getPhoneNum()+"\n\n" + mail.getMailContent());
        emailSender.send(message);
    }
}
