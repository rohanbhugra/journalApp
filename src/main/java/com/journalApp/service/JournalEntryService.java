package com.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.repo.JournalEntryRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class JournalEntryService {
	@Autowired
	private JournalEntryRepo journalRepo;
	
	@Autowired
	private UserService userService;
	
	
	
	@Transactional
	public void saveEntry(JournalEntry journalEntry, String userName) {
		User user = userService.findByUserName(userName);
		journalEntry.setDate(LocalDateTime.now());
		JournalEntry saved= journalRepo.save(journalEntry);
		user.getJournalEntries().add(saved);
		//user.setUserName(null);
		userService.saveUser(user);
	}
	
	public void saveEntry(JournalEntry journalEntry) {
		
		journalRepo.save(journalEntry);
	}
	
	public List<JournalEntry> getAll(){
		return journalRepo.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalRepo.findById(id);
	}
	@Transactional
	public boolean deleteById(ObjectId id,String username) {
		boolean removed= false;
		try {
		User user = userService.findByUserName(username);
		removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		if(removed) {
		userService.saveUser(user);
		journalRepo.deleteById(id);
		}
		
		}
		catch(Exception e) {
			log.error("Error: ",e);
			throw new RuntimeException("An error occured while deleting the entry.",e);
		}
        return removed;
		
		
	}
	
	
}
