package com.example.task_manager.Controllers;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Services.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
@Tag(name = "Проєкти")
public class ProjectController {
    private IProjectService service;

    @Operation(
            description = "Повертає список усіх проєктів які наявні у базі даних",
            summary = "Усі проєкти"
    )
    @GetMapping("/all-projects")
    public List<Project> getAllProjects(){
        return service.getAllProjects();
    }

    @Operation(
            description = "Створює проект за введеними даними у базі даних. Якщо ви не хочете задавати параметр команди то залиште там \"\"",
            summary = "Створити проєкт"
    )
    @PostMapping("/add-project")
    public void addProject(@RequestBody Project project){
        service.addProject(project);
    }

    @Operation(
            description = "Оновлює запис проєкту у базі даних",
            summary = "Оновити проєкт"
    )
    @PutMapping("/update-project")
    public void updateProject(@RequestBody Project project){
        service.updateProject(project);
    }

    @Operation(
            description = "Видаляє запис проєкту із бази даних",
            summary = "Видалити проєкт"
    )
    @DeleteMapping("/delete-project")
    public void deleteProject(@RequestBody Project project){
        service.deleteProject(project);
    }
}
