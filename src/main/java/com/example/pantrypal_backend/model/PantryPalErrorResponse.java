package com.example.pantrypal_backend.model;

public record PantryPalErrorResponse(
        String message,
        String error,
        String details
){}
