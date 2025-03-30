package com.journalApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalApp.service.EmailService;

@SpringBootTest
public class EmailServiceTests {

	@Autowired
	private EmailService emailService;
	
	@Test
	void testSendEmail() {
		emailService.sendEmail("rrbhugra20000@gmail.com","Testing Java Mail Sender", "Hi, Aap kaise hain??");
	}
}
