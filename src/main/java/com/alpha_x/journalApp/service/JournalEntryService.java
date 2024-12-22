package com.alpha_x.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alpha_x.journalApp.entity.JournalEntry;
import com.alpha_x.journalApp.entity.User;
import com.alpha_x.journalApp.repository.JournalEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;

  @Autowired
  private UserService userService;



  @Transactional
  public void saveEntry(JournalEntry journalEntry, String userName){
    try {
      User user = userService.findByUserName(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry saved = journalEntryRepository.save(journalEntry);
      user.getJournalEntries().add(saved);
      userService.saveUser(user);
     } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("An error occured while saving an enrty.", e);
    }
  }

  public void saveEntry(JournalEntry journalEntry){
      journalEntryRepository.save(journalEntry);
  }

  public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
  }

  public Optional<JournalEntry> findById(ObjectId myId){
    return journalEntryRepository.findById(myId);
  }

  @Transactional
  public boolean deleteById(ObjectId myId, String userName) {
    boolean bool = false;
    try {
      User user = userService.findByUserName(userName);
      bool = user.getJournalEntries().removeIf(entry -> entry.getId().equals(myId)); // Remove from user's list
      if (bool) {
        userService.saveUser(user); // Save updated user
        journalEntryRepository.deleteById(myId); // Delete entry from repository
      }
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("An Error Occured while deleting the entry.", e);
    }
    return bool;
}


}
