package com.example.task_manager.Services;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.ProjectRepository;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService implements ITeamService{
    private TeamRepository repos;
    private ProjectRepository projectRepos;
    private UserRepository userRepos;

    @Override
    public List<Team> getAllTeams() {
        return repos.findAll().stream()
                .peek(team ->{
                    Project project = projectRepos.findById(team.getProjectId()).orElse(null);
                    User user = userRepos.findById(team.getCreatorId()).orElse(null);
                    team.setCreator(user);
                    team.setProject(project);
                    if(user.getTeamId().equals("")){
                        user.setTeamId(team.getId());
                        userRepos.save(user);
                    }
                })
                .toList();
    }
    @Override
    public void addTeam(Team team) {
        repos.insert(team);
    }
    @Override
    public void updateTeam(Team team) {
        repos.save(team);
    }
    @Override
    public void deleteTeam(Team team) {
        repos.delete(team);
    }
    @Override
    public List<Team> teamByOrganisation(String organisationName) {
        return getAllTeams().stream()
                .filter(team -> team.getCompanyName().equals(organisationName))
                .toList();
    }
    @Override
    public List<Team> teamsByProject(Project project) {
        return getAllTeams().stream()
                .filter(team -> team.getProject().equals(project))
                .toList();
    }
}
