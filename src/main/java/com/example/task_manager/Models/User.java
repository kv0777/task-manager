package com.example.task_manager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String FullName;
    private String Email;
    private String Password;
    private String TeamId;

    @Transient
    private Team Team;
}
