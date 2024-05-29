package com.example.task_manager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    private String Name;
    private String Description;
    private LocalDate DateStart;
    private LocalDate DateEnd;
    private String UserId;
    private Boolean Status;
    private String TeamId;

    @Transient
    private User User;
    @Transient
    private Team Team;
}
