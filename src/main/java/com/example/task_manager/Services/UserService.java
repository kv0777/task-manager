package com.example.task_manager.Services;

import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Primary
public class UserService implements IUserService, UserDetailsService {
    private UserRepository repos;
    private TeamRepository teamRepos;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return repos.findAll().stream()
                .peek(user -> {
                    if(user.getTeamId() != ""){
                        Team team = teamRepos.findById(user.getTeamId()).orElse(null);
                        user.setTeam(team);
                    }
                })
                .collect(Collectors.toList());
    }
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repos.insert(user);
    }
    @Override
    public void updateUser(User user) {
        repos.save(user);
    }
    @Override
    public void deleteUser(User user) {
        repos.delete(user);
    }
    @Override
    public User userByEmail(String email) {
        return getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userByEmail(email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
