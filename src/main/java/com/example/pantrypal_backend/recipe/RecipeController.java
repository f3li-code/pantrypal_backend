package com.example.pantrypal_backend.recipe;

import com.example.pantrypal_backend.db.entity.RecipeEntity;
import com.example.pantrypal_backend.db.entity.UserEntity;
import com.example.pantrypal_backend.model.Recipe;
import com.example.pantrypal_backend.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping
    public void addRecipe(@AuthenticationPrincipal User user, @RequestBody Recipe recipe) {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        recipeService.addRecipe(userEntity, recipe);
    }

    @GetMapping
    public List<RecipeEntity> getAllRecipes(@AuthenticationPrincipal User user) {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        return recipeService.getAllRecipes(userEntity.id());
    }

    @PutMapping
    public void updateRecipeById(@AuthenticationPrincipal User user, @RequestBody Recipe recipe) throws UpdateRecipeDoesntExistException {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        recipeService.updateRecipeById(userEntity, recipe);
    }

    @DeleteMapping
    public void deleteRecipeById(@AuthenticationPrincipal User user, @RequestParam(value = "recipe_id", required = true) String recipeId) throws UpdateRecipeDoesntExistException {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        recipeService.deleteRecipeById(recipeId, userEntity.id());
    }
}
