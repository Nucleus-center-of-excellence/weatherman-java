package com.org.project.twm.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	public void sendEmail(SimpleMailMessage email);
}	
