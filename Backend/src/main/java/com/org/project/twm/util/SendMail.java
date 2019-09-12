package com.org.project.twm.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class SendMail {

	@Autowired
	private JavaMailSender sender;
	
	public String sendMail(String username, String token) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(username);
			// helper.setText(mailContentBuilder.build(token),true);
			helper.setText(
					"Weather Man Account completed please set your password before 24 hours on given link " + token);
			helper.setSubject("The Weather Man Registration Completed");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}

		sender.send(message);
		return "Mail Sent Success!";
	}
}
