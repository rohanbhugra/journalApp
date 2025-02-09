package com.journalApp.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.entity.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry,ObjectId> {

}
