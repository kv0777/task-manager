package com.example.task_manager.Controllers;

import com.example.task_manager.Models.Project;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Services.ITeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@AllArgsConstructor
@Tag(name = "Команди")
public class TeamController {
    private ITeamService service;

    @Operation(
            description = "Повертає список усіх команд наявних у базі даних",
            summary = "Усі команди"
    )
    @GetMapping("/all-teams")
    public List<Team> getAllTeams(){
        return service.getAllTeams();
    }
    @Operation(
            description = "Створює команду у базі даних",
            summary = "Створити команду"
    )
    @PostMapping("/add-team")
    public void addTeam(@RequestBody Team team){
        service.addTeam(team);
    }
    @Operation(
            description = "Оновлює команду у базі даних",
            summary = "Оновити команду"
    )
    @PutMapping("/update-team")
    public void updateTeam(@RequestBody Team team){
        service.updateTeam(team);
    }
    @Operation(
            description = "Видаляє команду із бази даних",
            summary = "Видалити команду"
    )
    @DeleteMapping("/delete-team")
    public void deleteTeam(@RequestBody Team team){
        service.deleteTeam(team);
    }
    @Operation(
            description = "Видаляє команду у базі даних",
            summary = "Видалити команду"
    )
    @PostMapping("/teams-by-organisation/{organisation}")
    public List<Team> teamsByOrganisation(@RequestAttribute String organisation){
        return service.teamByOrganisation(organisation);
    }
    @Operation(
            description = "Повертає увесь список команд які працюють над вказаним проєктом",
            summary = "Усі команди за проєктом"
    )
    @PostMapping("teams-by-project")
    public List<Team> teamsByProject(@RequestBody Project project){
        return service.teamsByProject(project);
    }
}
