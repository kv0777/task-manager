package com.example.task_manager.Services;

import com.example.task_manager.Models.Project;

import java.util.List;

public interface IProjectService {
    public List<Project> getAllProjects();
    public void addProject(Project project);
    public void updateProject(Project project);
    public void deleteProject(Project project);
}
