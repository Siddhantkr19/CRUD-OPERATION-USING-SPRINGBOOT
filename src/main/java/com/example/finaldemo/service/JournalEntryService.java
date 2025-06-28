package com.example.finaldemo.service;

import com.example.finaldemo.Entity.JournalEntry;
import com.example.finaldemo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
    public JournalEntry updateJournalEntryById(ObjectId id, JournalEntry updatedEntry) {
        JournalEntry existingEntry = journalEntryRepository.findById(id).orElse(null);

        if (existingEntry != null) {
            existingEntry.setTitle(updatedEntry.getTitle());
            existingEntry.setContent(updatedEntry.getContent());

            // Update other fields as needed
            return journalEntryRepository.save(existingEntry);
        }

        return null; // Or throw a custom exception if preferred
    }

}

