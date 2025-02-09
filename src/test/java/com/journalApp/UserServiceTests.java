package com.journalApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journalApp.entity.User;
import com.journalApp.repo.UserRepo;
/*
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserRepo userRepo;
	
	@Disabled
	@Test
	
	public void testFindByUserName() {
		User user = userRepo.findByUserName("Rohan");
		assertNotNull(user);
		assertTrue(!user.getJournalEntries().isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,10,12",
		"3,3,9"
	})
	public void test(int a, int b, int expected) {
		assertEquals(expected,a+b);
	}

}
*/