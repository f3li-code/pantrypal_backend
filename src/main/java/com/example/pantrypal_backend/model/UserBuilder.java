package com.example.pantrypal_backend.model;

import com.example.pantrypal_backend.db.entity.UserEntity;

import java.util.UUID;

public class UserBuilder {
    private String userId;
    private String username;
    private String password;

    public UserBuilder() {
        userId = "user_" + UUID.randomUUID();
        username = null;
        password = null;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity build() {
        if (username == null) username = "username_" + UUID.randomUUID();
        if (password == null) password = "password_" + UUID.randomUUID();
        return new UserEntity(userId, username);
    }
}
