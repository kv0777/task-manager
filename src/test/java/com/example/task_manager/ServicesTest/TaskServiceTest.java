package com.example.task_manager.ServicesTest;

import com.example.task_manager.Models.Task;
import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import com.example.task_manager.Repositories.TaskRepository;
import com.example.task_manager.Repositories.TeamRepository;
import com.example.task_manager.Repositories.UserRepository;
import com.example.task_manager.Services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", null, null);
        User user = new User("user1", "Jane Doe", "jane.doe@example.com", "password", "team1", null);
        Team team = new Team("team1", "Team A", "Company A", "creator1", "project1", null, null);
        when(taskRepository.findAll()).thenReturn(List.of(task));
        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
        when(teamRepository.findById("team1")).thenReturn(Optional.of(team));

        List<Task> tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task A", tasks.get(0).getName());
        assertNotNull(tasks.get(0).getUser());
        assertEquals("Jane Doe", tasks.get(0).getUser().getFullName());
        assertNotNull(tasks.get(0).getTeam());
        assertEquals("Team A", tasks.get(0).getTeam().getName());
    }

    @Test
    void testAddTask() {
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", null, null);

        taskService.addTask(task);

        verify(taskRepository, times(1)).insert(task);
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", null, null);

        taskService.updateTask(task);

        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", null, null);

        taskService.deleteTask(task);

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testTasksByUser() {
        User user = new User("user1", "Jane Doe", "jane.doe@example.com", "password", "team1", null);
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", user, null);
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.tasksByUser(user);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task A", tasks.get(0).getName());
        assertEquals("Jane Doe", tasks.get(0).getUser().getFullName());
    }

    @Test
    void testOverdueTasks() {
        Task task = new Task("1", "Task A", "Description A", LocalDate.now().minusDays(10), LocalDate.now().minusDays(5), "user1", false, "team1", null, null);
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.overdueTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0).getDateEnd().isBefore(LocalDate.now()));
    }

    @Test
    void testTasksByTeam() {
        Team team = new Team("team1", "Team A", "Company A", "creator1", "project1", null, null);
        Task task = new Task("1", "Task A", "Description A", LocalDate.now(), LocalDate.now().plusDays(5), "user1", false, "team1", null, team);
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.tasksByTeam(team);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task A", tasks.get(0).getName());
        assertEquals("Team A", tasks.get(0).getTeam().getName());
    }
}
