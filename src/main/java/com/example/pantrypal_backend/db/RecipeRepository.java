package com.example.pantrypal_backend.db;

import com.example.pantrypal_backend.db.entity.RecipeEntity;
import com.example.pantrypal_backend.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<RecipeEntity, String> {
    public List<RecipeEntity> findRecipeEntitiesByUserId(String userId);

    public RecipeEntity findRecipeEntityById(String id);
}
