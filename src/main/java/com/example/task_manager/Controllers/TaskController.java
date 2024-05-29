package com.example.task_manager.Controllers;

import com.example.task_manager.Models.Task;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Services.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@Tag(name = "Завдання")
public class TaskController {
    private ITaskService service;

    @Operation(
            description = "Повертає список усіх завдань наявних у базі даних",
            summary = "Усі завдання"
    )
    @GetMapping("/all-tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }
    @Operation(
            description = "Створює завдання у базі даних",
            summary = "Створення завдання"
    )
    @PostMapping("/add-task")
    public void addTask(@RequestBody Task task){
        service.addTask(task);
    }
    @Operation(
            description = "Оновлює завдання у базі даних",
            summary = "Оновити завдання"
    )
    @PutMapping("/update-task")
    public void updateTask(@RequestBody Task task){
        service.updateTask(task);
    }

    @Operation(
            description = "Видаляє завдання у базі даних",
            summary = "Видалити завдання"
    )
    @DeleteMapping("/delete-task")
    public void deleteTask(@RequestBody Task task){
        service.deleteTask(task);
    }
    @Operation(
            description = "Повертає список усіх завдань вказаного користувача із бази даних",
            summary = "Завдання користувача"
    )
    @PostMapping("/tasks-by-user")
    public List<Task> getTasksByUser(@RequestBody User user){
        return service.tasksByUser(user);
    }
    @Operation(
            description = "Повертає список усіх просрочених завдань із бази даних",
            summary = "Просрочені завдання"
    )
    @GetMapping("/overdue-tasks")
    public List<Task> getOverdueTasks(){
        return service.overdueTasks();
    }
    @Operation(
            description = "Повертає список усіх завдань вказаної команди  із бази даних",
            summary = "Завдання команди"
    )
    @PostMapping("/tasks-by-team")
    public List<Task> getTasksByTeam(@RequestBody Team team){
        return service.tasksByTeam(team);
    }

    @Operation(
            description = "Завершує обране користувачем завдання",
            summary = "Завершення завдання"
    )
    @PostMapping("/end-task")
    public String endTask(@RequestBody Task task){
        if(!task.getStatus()){
            return "Завдання вже виконано";
        }
        service.endTask(task);
        return "Завдання успішно завершене";
    }
}
