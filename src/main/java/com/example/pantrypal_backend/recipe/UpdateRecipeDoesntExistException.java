package com.example.pantrypal_backend.recipe;

public class UpdateRecipeDoesntExistException extends Exception{
    public UpdateRecipeDoesntExistException(String msg) {
        super(msg);
    }
}
