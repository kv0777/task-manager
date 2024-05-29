package com.example.task_manager.Services;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService implements IProjectService{
    private ProjectRepository repos;
    @Override
    public List<Project> getAllProjects() {
        return repos.findAll();
    }
    @Override
    public void addProject(Project project) {
        repos.insert(project);
    }
    @Override
    public void updateProject(Project project) {
        repos.save(project);
    }
    @Override
    public void deleteProject(Project project) {
        repos.delete(project);
    }
}
