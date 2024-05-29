package com.example.task_manager.Services;

import com.example.task_manager.Models.Task;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.TaskRepository;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService{
    private TaskRepository repos;
    private UserRepository userRepos;
    private TeamRepository teamRepos;

    @Override
    public List<Task> getAllTasks() {
        return repos.findAll().stream()
                .peek(task ->{
                    User user = userRepos.findById(task.getUserId()).orElse(null);
                    Team team = teamRepos.findById(task.getTeamId()).orElse(null);
                    task.setUser(user);
                    task.setTeam(team);
                }).toList();
    }
    @Override
    public void addTask(Task task) {
        repos.insert(task);
    }
    @Override
    public void updateTask(Task task) {
        repos.save(task);
    }
    @Override
    public void deleteTask(Task task) {
        repos.delete(task);
    }
    @Override
    public List<Task> tasksByUser(User user) {
        return getAllTasks().stream()
                .filter(task -> task.getUser().equals(user))
                .toList();
    }
    @Override
    public List<Task> overdueTasks() {
        return getAllTasks().stream()
                .filter(task -> task.getDateEnd().isBefore(LocalDate.now()))
                .toList();
    }
    @Override
    public List<Task> tasksByTeam(Team team) {
        return getAllTasks().stream()
                .filter(task -> task.getTeam().equals(team))
                .toList();
    }

    @Override
    public void endTask(Task task) {
        task.setStatus(false);
        repos.save(task);
    }
}
