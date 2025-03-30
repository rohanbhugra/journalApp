package com.journalApp.controller;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.api.response.WeatherResponse;
import com.journalApp.entity.User;
import com.journalApp.repo.UserRepo;
import com.journalApp.service.UserService;
import com.journalApp.service.WeatherService;

@RestController
@RequestMapping("/user")
public class UserEntryController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	WeatherService weatherService;
	/*
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<User> all = userService.getAll();
    	if(all!=null && !all.isEmpty()) {
    		return new ResponseEntity<>(all,HttpStatus.OK);
    	}
        return new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
	}
	*/
	
	
	@GetMapping("/userid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable ObjectId id){
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//String userName = authentication.getName();
		userRepo.deleteByUserName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} 
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userInDB= userService.findByUserName(userName);
		if(userInDB!=null) {
			userInDB.setUserName(user.getUserName());
			userInDB.setPassword(user.getPassword());
			userService.saveNewUser(userInDB);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<?> greeting(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
		String greeting= "";
		if(weatherResponse!=null) {
			greeting = ", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();
		}
		
		return new ResponseEntity<>("Hi "+ authentication.getName()+ greeting,HttpStatus.OK);
	}
}
