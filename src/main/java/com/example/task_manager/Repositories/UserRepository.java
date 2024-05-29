package com.example.task_manager.Repositories;

import com.example.task_manager.Models.Team;
import com.example.task_manager.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
