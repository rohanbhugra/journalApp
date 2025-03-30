package com.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.entity.ConfigJournalAppEntity;
import com.journalApp.repo.ConfigJournalAppRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
	
	public enum keys{
		WEATHER_API;
	}
	
	@Autowired
	private ConfigJournalAppRepo configJournalAppRepo;
	
	public Map<String,String> appCache;
	
	@PostConstruct
	public void init() {
		appCache = new HashMap<>();
		List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
		for(ConfigJournalAppEntity configJournalAppEntity : all) {
			appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
		}
		
	}
}
