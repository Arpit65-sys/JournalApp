package com.alpha_x.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.alpha_x.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
  User findByUserName(String username);
  void deleteByUserName(String userName);
}

