package com.journalApp;

import static org.mockito.Mockito.when;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

//import com.journalApp.entity.User;
import com.journalApp.repo.UserRepo;
import com.journalApp.service.UserDetailsServiceImpl;
//import com.journalApp.service.UserDetailsServiceImpl;
/*
public class UserDetailsServiceImplTests {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Mock
	private UserRepo userRepo;
	/*
	void loadUserByUserNameTest() {
		when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Rohan").password("fsesgsr").build());
		UserDetails user = userDetailsService.loadUserByUsername("Rohan");
	}
	
	
}
*/