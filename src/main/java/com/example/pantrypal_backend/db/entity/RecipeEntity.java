package com.example.pantrypal_backend.db.entity;

import com.example.pantrypal_backend.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipe")
public class RecipeEntity {
    @Id public String id;
    @JsonProperty public String userId;
    @JsonProperty public String title;
    @JsonProperty("meal_type") public String mealType;
    @JsonProperty public String instructions;
    @JsonProperty public String ingredients;
    @JsonProperty public String imgBase64Str;

    public RecipeEntity(UserEntity userEntity, Recipe recipe) {
        this.userId = userEntity.id();
        this.id = recipe.id;
        this.title = recipe.title;
        this.mealType = recipe.mealType;
        this.instructions = recipe.instructions;
        this.ingredients = recipe.ingredients;
        this.imgBase64Str = recipe.imgBase64Str;
    }
}



