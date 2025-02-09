package com.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.journalApp.entity.User;
import com.journalApp.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// instead of creating instance of logger we can use @Slf4j
	//private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	
	public boolean saveNewUser(User user) {
		try {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userRepo.save(user);
		return true;
		}
		catch(Exception e) {
			log.info("hahahaha");
			log.error("hahahaa");
			log.warn("hahahaha");
			log.debug("hahahaha");
		    log.trace("hahahaha");

			return false;
		}
	}
	
	public void saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userRepo.save(user);
	}
	
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	public List<User> getAll(){
		return userRepo.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepo.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		userRepo.deleteById(id);
	}
	
	public User findByUserName(String username) {
		return userRepo.findByUserName(username);
	}
}
