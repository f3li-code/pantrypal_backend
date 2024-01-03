package com.example.pantrypal_backend.db;

import com.example.pantrypal_backend.db.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findUserByUsername(String username);
}
