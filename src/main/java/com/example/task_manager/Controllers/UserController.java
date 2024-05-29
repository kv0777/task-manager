package com.example.task_manager.Controllers;

import com.example.task_manager.Models.User;
import com.example.task_manager.Services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "Користувачі")
public class UserController {
    private IUserService service;

    @Operation(
            description = "Повертає список усіх користувачів наявних у базі даних",
            summary = "Усі користувачі"
    )
    @GetMapping("/all-users")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @Operation(
            description = "Створює користувача у базі даних",
            summary = "Створити користувача"
    )
    @PostMapping("/add-user")
    public void addUser(@RequestBody User user){
        service.addUser(user);
    }
    @Operation(
            description = "Оновлює користувача у базі даних",
            summary = "Оновити користувача"
    )
    @PutMapping("/update-user")
    public void updateUser(@RequestBody User user){
        service.updateUser(user);
    }
    @Operation(
            description = "Видаляє користувача із бази даних",
            summary = "Видалити користувача"
    )
    @DeleteMapping("/delete-mapping")
    public void deleteUser(User user){
        service.deleteUser(user);
    }
    @Operation(
            description = "Повертає користувача за вказаним E-Mail",
            summary = "Користувач за поштою"
    )
    @GetMapping("/user-by-email/{email}")
    public User userByEmail(@PathVariable String email){
        return service.userByEmail(email);
    }
}
