package com.example.task_manager.Controllers;

import com.example.task_manager.Configurations.SecurityConfig;
import com.example.task_manager.Models.User;
import com.example.task_manager.Services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аунтентифікація")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final SecurityConfig config;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserService userService, SecurityConfig config) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.config = config;
    }

    @Operation(
            description = "Авторизація користувача у системі за допомогою його email та паролю",
            summary = "Авторизація"
    )
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Успішна авторизація";
        } catch (AuthenticationException e) {
            return "Помилка авторизації:  " + e.getMessage();
        }
    }

    @Operation(
            description = "Регіструє користувача у системі. Якщо ви не хочете задавати параметр команди то залиште там \"\"",
            summary = "Регістрація"
    )
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userService.addUser(user);
        return "Успішна регістрація";
    }
}
