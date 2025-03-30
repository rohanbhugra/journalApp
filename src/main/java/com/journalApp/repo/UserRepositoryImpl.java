package com.journalApp.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.journalApp.entity.User;
@Component
public class UserRepositoryImpl {
	
	@Autowired
	private MongoTemplate mongoTemplate; 
	
	public List<User> getUserForSA(){
		Query query = new Query();
		query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
		//query.addCriteria(Criteria.where("email").ne(null).ne(""));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		Criteria criteria = new Criteria();
		
		List<User> users= mongoTemplate.find(query,User.class);
		return users;
		
		
	}

}
