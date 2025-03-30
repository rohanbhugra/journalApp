package com.journalApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalApp.repo.UserRepositoryImpl;

@SpringBootTest
public class UserRepositoryImplTests {
	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@Test
	public void testGetUserForSA() {
		Assertions.assertNotNull(userRepositoryImpl.getUserForSA());	
		}
}
