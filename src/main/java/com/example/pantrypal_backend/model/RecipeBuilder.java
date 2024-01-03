package com.example.pantrypal_backend.model;

import java.util.UUID;

public class RecipeBuilder {
    String title;
    String mealType;
    String instructions;
    String ingredients;
    String id;

    String imgBase64Str;

    public RecipeBuilder() {
        this.title  = "Dummy Title";
        this.mealType = "Dummy Meal Type";
        this.instructions = "Dummy Instructions\ninstruction Line 2";
        this.ingredients = "Dummy Ingredients\ningredients Line 2\ningredients Line 3";
        this.id = "recipe_" + UUID.randomUUID();
        this.imgBase64Str = null;
    }

    public RecipeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    public RecipeBuilder setMealType(String mealType) {
        this.mealType = mealType;
        return this;
    }
    public RecipeBuilder setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
    public RecipeBuilder setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder setImageBase64Str(String imgBase64Str) {
        this.imgBase64Str = imgBase64Str;
        return this;
    }
    public RecipeBuilder setId(String id) {
        this.id = id;
        return this;
    }
    public Recipe build() {
        return new Recipe(title, mealType, instructions, ingredients, id, imgBase64Str);
    }
}