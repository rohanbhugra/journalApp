package com.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.entity.User;
import com.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private UserService userService;
	
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
    
    @PostMapping("/create-user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			userService.saveNewUser(user);
			return new ResponseEntity<>(user,HttpStatus.CREATED);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
		}
	}
}
