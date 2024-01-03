package com.example.pantrypal_backend.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public record UserEntity(
        @Id String id,
        String username
){}
