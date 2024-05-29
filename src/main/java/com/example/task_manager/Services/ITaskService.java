package com.example.task_manager.Services;

import com.example.task_manager.Models.Task;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;

import java.util.List;

public interface ITaskService {
    public List<Task> getAllTasks();
    public void addTask(Task task);
    public void updateTask(Task task);
    public void deleteTask(Task task);
    public List<Task> tasksByUser(User user);
    public List<Task> overdueTasks();
    public List<Task> tasksByTeam(Team team);

    void endTask(Task task);
}
