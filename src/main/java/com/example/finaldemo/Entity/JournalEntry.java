package com.example.finaldemo.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document (collection = "journal")
@Data
@NoArgsConstructor
public class JournalEntry {




    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
}