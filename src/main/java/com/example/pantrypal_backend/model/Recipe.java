package com.example.pantrypal_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Recipe {
    @JsonProperty public String title;
    @JsonProperty("meal_type") public String mealType;
    @JsonProperty public String instructions;
    @JsonProperty public String ingredients;
    @JsonProperty public String id;

    @JsonProperty public String imgBase64Str;

    public Recipe(String title, String mealType, String instructions, String ingredients, String id, String imgBase64Str) {
        this.title = title;
        this.mealType = mealType;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.id = id;
        this.imgBase64Str = imgBase64Str;
    }

    @Override
    public String toString() {
        return "\n==============\n" +
                id + title + '\n' +
                mealType + '\n' +
                ingredients +
                instructions +
                imgBase64Str.substring(0, 10) + '\n' +
                "==============\n" ;
    }

//    private String printArr(String[] arr) {
//        StringBuilder result = new StringBuilder();
//        for (String s : arr) {
//            result.append(s).append('\n');
//        }
//        return result.toString();
//    }
}
