package com.example.task_manager.ServicesTest;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Repositories.ProjectRepository;
import com.example.task_manager.Services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        Project project = new Project("1", "Project A", "Description A");
        when(projectRepository.findAll()).thenReturn(List.of(project));

        List<Project> projects = projectService.getAllProjects();

        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals("Project A", projects.get(0).getName());
    }

    @Test
    void testAddProject() {
        Project project = new Project("1", "Project A", "Description A");

        projectService.addProject(project);

        verify(projectRepository, times(1)).insert(project);
    }

    @Test
    void testUpdateProject() {
        Project project = new Project("1", "Project A", "Description A");

        projectService.updateProject(project);

        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDeleteProject() {
        Project project = new Project("1", "Project A", "Description A");

        projectService.deleteProject(project);

        verify(projectRepository, times(1)).delete(project);
    }
}
