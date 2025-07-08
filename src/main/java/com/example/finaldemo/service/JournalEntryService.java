package com.example.finaldemo.service;

import com.example.finaldemo.Entity.JournalEntry;
import com.example.finaldemo.Entity.User;
import com.example.finaldemo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
@Autowired
private UserService userService ;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("User not found: " + userName);
        }

        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    };

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
        User   user  = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userService.saveEntry(user);



        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateUserJournalEntry(String userName, ObjectId id, JournalEntry updatedEntry) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("User not found: " + userName);
        }

        for (JournalEntry entry : user.getJournalEntries()) {
            if (entry.getId().equals(id)) {
                entry.setTitle(updatedEntry.getTitle());
                entry.setContent(updatedEntry.getContent());
                // Add additional field updates here if needed

                userService.saveEntry(user); // Update user
                return journalEntryRepository.save(entry); // Update journal entry
            }
        }

        throw new RuntimeException("Journal entry not found for ID: " + id);
    }

}

