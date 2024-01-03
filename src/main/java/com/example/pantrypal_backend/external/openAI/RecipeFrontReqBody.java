package com.example.pantrypal_backend.external.openAI;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecipeFrontReqBody(
        @JsonProperty("meal_type") String mealType,
        @JsonProperty("ingredients") String ingredients,
        @JsonProperty("is_real") Boolean isReal,
        @JsonProperty("with_image") Boolean withImage
) {}
