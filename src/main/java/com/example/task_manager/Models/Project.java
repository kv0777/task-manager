package com.example.task_manager.Models;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "project")
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private String id;
    private String Name;
    private String Description;
}
