package com.tejas.F2B_Farmer.request;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRequest {

	    @Autowired
	    private JavaMailSender javaMailSender;

	    @Value("${spring.mail.username}")
	    private String fromEmailId;

	    public void sendEmail(String to, String body, String subject) {
	        try {
	            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	            simpleMailMessage.setFrom(fromEmailId);
	            simpleMailMessage.setTo(to);
	            simpleMailMessage.setText(body);
	            simpleMailMessage.setSubject(subject);

	            javaMailSender.send(simpleMailMessage);
	        } catch (Exception e) {
	            throw new RuntimeException("Error while sending email: " + e.getMessage());
	        }
	    }
	}
	


