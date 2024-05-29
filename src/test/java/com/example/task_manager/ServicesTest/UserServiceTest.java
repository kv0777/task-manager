package com.example.task_manager.ServicesTest;

import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import com.example.task_manager.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user = new User("1", "Тест Тестува", "test.tst@test.com", "123", "команда1", null);
        Team team = new Team("команда1", "Команда 1", "Компания 1", "1", "проект1", null, null);
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(teamRepository.findById("команда1")).thenReturn(Optional.of(team));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Тест Тестува", users.get(0).getFullName());
        assertNotNull(users.get(0).getTeam());
        assertEquals("Команда 1", users.get(0).getTeam().getName());
    }

    @Test
    void testAddUser() {
        User user = new User("1", "Тест Тестува", "test.tst@test.com", "123", "команда1", null);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        userService.addUser(user);

        verify(userRepository, times(1)).insert(any(User.class));
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void testUpdateUser() {
        User user = new User("1", "Тест Тестува2", "test.tst@test.com", "123", "команда1", null);

        userService.updateUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        User user = new User("1", "Тест Тестува2", "test.tst@test.com", "123", "команда1", null);

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        User user = new User("1", "Тест Тестува", "test.tst@test.com", "123", "команда1", null);
        when(userRepository.findAll()).thenReturn(List.of(user));

        UserDetails userDetails = userService.loadUserByUsername("Тест Тестува");

        assertNotNull(userDetails);
        assertEquals("test.tst@test.com", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("1231@test.com");
        });
    }
}
