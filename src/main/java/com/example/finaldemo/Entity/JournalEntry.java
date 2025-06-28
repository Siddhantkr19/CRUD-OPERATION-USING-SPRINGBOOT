package com.example.finaldemo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document (collection = "journal")
@Data
public class JournalEntry {




    @Id
    private ObjectId id;
    private String title;
    private String content;
}