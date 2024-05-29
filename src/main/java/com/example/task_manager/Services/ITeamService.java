package com.example.task_manager.Services;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Models.Task;
import com.example.task_manager.Models.Team;

import java.util.List;

public interface ITeamService {
    public List<Team> getAllTeams();
    public void addTeam(Team team);
    public void updateTeam(Team team);
    public void deleteTeam(Team team);
    public List<Team> teamByOrganisation(String organisationName);
    public List<Team> teamsByProject(Project project);
}
