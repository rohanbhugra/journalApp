package com.journalApp.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.entity.ConfigJournalAppEntity;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity,ObjectId>{
	
}


