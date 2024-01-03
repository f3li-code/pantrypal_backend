package com.example.pantrypal_backend.recipe;

import com.example.pantrypal_backend.db.RecipeRepository;
import com.example.pantrypal_backend.db.entity.RecipeEntity;
import com.example.pantrypal_backend.db.entity.UserEntity;
import com.example.pantrypal_backend.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
//    @Autowired
    private final MongoTemplate mongoTemplate;
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository, MongoTemplate mongoTemplate) {
        this.recipeRepository = recipeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void addRecipe(UserEntity userEntity, Recipe recipe) {

        recipeRepository.save(new RecipeEntity(userEntity, recipe));
    }

    public List<RecipeEntity> getAllRecipes(String userId) {
        return recipeRepository.findRecipeEntitiesByUserId(userId);
    }

    public void updateRecipeById(UserEntity userEntity, Recipe recipe) throws UpdateRecipeDoesntExistException {
        String id = recipe.id;
        RecipeEntity oldRecipe = recipeRepository.findRecipeEntityById(id);
        if (oldRecipe == null) {
            throw new UpdateRecipeDoesntExistException("The recipe you are trying to update does not exist!");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userEntity.id()));
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.findAndReplace(query, recipe, new FindAndReplaceOptions(), Recipe.class, "recipe", Recipe.class);
    }

    public void deleteRecipeById(String recipeId, String userId) throws UpdateRecipeDoesntExistException{
        RecipeEntity oldRecipe = recipeRepository.findRecipeEntityById(recipeId);
        if (oldRecipe == null || !oldRecipe.userId.equals(userId)) {
            throw new UpdateRecipeDoesntExistException("The recipe you are trying to delete does not exist!");
        }
        recipeRepository.deleteById(recipeId);
    }
}
