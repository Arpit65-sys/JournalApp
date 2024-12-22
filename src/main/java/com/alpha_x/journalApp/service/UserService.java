package com.alpha_x.journalApp.service;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alpha_x.journalApp.controller.JournalEntryControllerV2;
import com.alpha_x.journalApp.entity.User;
import com.alpha_x.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {

  @Autowired
  private UserRepository userRepository;

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private static final Logger logger = LoggerFactory.getLogger(JournalEntryControllerV2.class);

  public boolean saveNewUser(User user) {
    try {

      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(Arrays.asList("USER"));
      userRepository.save(user);
      return true;

    } catch (Exception e) {
      logger.error("error occured");
      logger.warn("warning to you");
      logger.info("this is info");
      logger.debug("dedededde");
      logger.trace("traceing you");
      return false;
    }
  }

  public void saveAdmin(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList("ADMIN", "USER"));
    userRepository.save(user);
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public void deleteById(ObjectId myId) {
    User entry = userRepository.findById(myId).orElse(null);
    if (entry != null) {
      userRepository.delete(entry);
    }
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

}
