package com.example.finaldemo.controller;

import com.example.finaldemo.Entity.JournalEntry;

import com.example.finaldemo.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")


public class JournalEntryController {
@Autowired
     private JournalEntryService journalEntryService ;

        @GetMapping
        public List<JournalEntry> getAll() {
       return journalEntryService.getAll();
        }

        @PostMapping
        public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
            return  true;
        }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/id/{myId}")


    public ResponseEntity<Void> deleteJournalEntry(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId); // Your service method
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        return journalEntryService.updateJournalEntryById(id, myEntry);
    }




    }

