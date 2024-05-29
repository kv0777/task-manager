package com.example.task_manager.ServicesTest;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.ProjectRepository;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import com.example.task_manager.Services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTeams() {
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", null, null);
        Project project = new Project("project1", "Project A", "Description A");
        User user = new User("creator1", "Jane Doe", "jane.doe@example.com", "password", "", null);
        when(teamRepository.findAll()).thenReturn(List.of(team));
        when(projectRepository.findById("project1")).thenReturn(Optional.of(project));
        when(userRepository.findById("creator1")).thenReturn(Optional.of(user));

        List<Team> teams = teamService.getAllTeams();

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertEquals("Team A", teams.get(0).getName());
        assertNotNull(teams.get(0).getProject());
        assertEquals("Project A", teams.get(0).getProject().getName());
        assertNotNull(teams.get(0).getCreator());
        assertEquals("Jane Doe", teams.get(0).getCreator().getFullName());
        assertEquals("1", user.getTeamId());
    }

    @Test
    void testAddTeam() {
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", null, null);

        teamService.addTeam(team);

        verify(teamRepository, times(1)).insert(team);
    }

    @Test
    void testUpdateTeam() {
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", null, null);

        teamService.updateTeam(team);

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void testDeleteTeam() {
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", null, null);

        teamService.deleteTeam(team);

        verify(teamRepository, times(1)).delete(team);
    }

    @Test
    void testTeamByOrganisation() {
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", null, null);
        when(teamRepository.findAll()).thenReturn(List.of(team));

        List<Team> teams = teamService.teamByOrganisation("Company A");

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertEquals("Team A", teams.get(0).getName());
        assertEquals("Company A", teams.get(0).getCompanyName());
    }

    @Test
    void testTeamsByProject() {
        Project project = new Project("project1", "Project A", "Description A");
        Team team = new Team("1", "Team A", "Company A", "creator1", "project1", project, null);
        when(teamRepository.findAll()).thenReturn(List.of(team));

        List<Team> teams = teamService.teamsByProject(project);

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertEquals("Team A", teams.get(0).getName());
        assertEquals("Project A", teams.get(0).getProject().getName());
    }
}
