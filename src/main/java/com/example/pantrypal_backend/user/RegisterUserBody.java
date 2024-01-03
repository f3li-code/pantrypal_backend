package com.example.pantrypal_backend.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterUserBody(
        @JsonProperty String username,
        @JsonProperty String password
) {}
