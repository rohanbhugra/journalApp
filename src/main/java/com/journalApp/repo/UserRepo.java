package com.journalApp.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.entity.User;

public interface UserRepo extends MongoRepository<User,ObjectId> {
	User findByUserName(String username);
	void deleteByUserName(String username);
}
