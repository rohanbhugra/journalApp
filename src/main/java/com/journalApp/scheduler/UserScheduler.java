package com.journalApp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.journalApp.cache.AppCache;
import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.enums.Sentiment;
import com.journalApp.repo.UserRepositoryImpl;
import com.journalApp.service.EmailService;
import com.journalApp.service.SentimentAnalysisService;

@Component
public class UserScheduler {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepositoryImpl userRepo;
	
	@Autowired
	private SentimentAnalysisService sentimentAnalysisService;
	
	@Autowired
	private AppCache appCache;
	
	@Scheduled(cron ="0 0 9 * * SUN")
	public void fetchUsersAndSendSaMail() {
		List<User> users = userRepo.getUserForSA();
		for(User user: users) {
			List<JournalEntry> journalEntries = user.getJournalEntries();
			List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
			Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
			for(Sentiment sentiment : sentiments) {
				if(sentiment!= null) {
					sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
				}
				
				Sentiment mostFrequentSentiment = null;
				int maxCount =0;
				for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet()) {
					if(entry.getValue() > maxCount) {
						maxCount = entry.getValue();
						mostFrequentSentiment = entry.getKey();
					}
				}
				if(mostFrequentSentiment !=null) {
					emailService.sendEmail(user.getEmail(),"Sentiment for Last 7 days", mostFrequentSentiment.toString());
				}
			}
		}
		
	}
	
	@Scheduled(cron = "0 0/10 * ? * *")
	public void clearCache() {
		appCache.init();
	}
}
