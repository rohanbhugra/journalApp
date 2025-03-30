package com.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.entity.User;
import com.journalApp.service.UserDetailsServiceImpl;
import com.journalApp.service.UserService;
import com.journalApp.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
    
    @PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody User user){
		try {
			userService.saveNewUser(user);
			return new ResponseEntity<>(user,HttpStatus.CREATED);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
		}
	}
    
    @PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
			String jwt = jwtUtil.generateToken(userDetails.getUsername());
		    return new ResponseEntity<>(jwt,HttpStatus.OK);
			
		}
		catch(Exception e) { 
			log.error("Exception occured while createAuthenticationToken ",e);
			return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
		}
	}
}
