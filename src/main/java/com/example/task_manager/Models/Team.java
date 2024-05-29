package com.example.task_manager.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "team")
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    private String id;
    private String Name;
    private String CompanyName;
    private String CreatorId;
    private String ProjectId;

    @Transient
    private Project Project;
    @Transient
    private User Creator;
}
