package com.example.task_manager.Services;

import com.example.task_manager.Models.User;

import java.util.List;

public interface IUserService {
    public List<User> getAllUsers();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User userByEmail(String email);
}
