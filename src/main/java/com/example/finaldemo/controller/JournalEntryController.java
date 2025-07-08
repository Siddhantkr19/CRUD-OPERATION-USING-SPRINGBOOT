package com.example.finaldemo.controller;

import com.example.finaldemo.Entity.JournalEntry;

import com.example.finaldemo.Entity.User;
import com.example.finaldemo.service.JournalEntryService;
import com.example.finaldemo.service.UserService;
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
@Autowired
private UserService userService ;
        @GetMapping("{userName}")


        public List<JournalEntry> getAllJournalEntriesOfUser(@PathVariable String userName) {
            User user = userService.findByUserName(userName);
       return user.getJournalEntries();
        }

        @PostMapping("{userName}")
        public boolean createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {

        journalEntryService.saveEntry(myEntry , userName);
            return  true ;

        }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/id/{userName}/{myId}")


    public ResponseEntity<Void> deleteJournalEntry(@PathVariable ObjectId myId ,@PathVariable String userName) {
        journalEntryService.deleteById(myId , userName); // Your service method
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }
    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable String userName,
                                                               @PathVariable ObjectId id,
                                                               @RequestBody JournalEntry updatedEntry) {
        JournalEntry result = journalEntryService.updateUserJournalEntry(userName, id, updatedEntry);
        return ResponseEntity.ok(result);
    }




    }

