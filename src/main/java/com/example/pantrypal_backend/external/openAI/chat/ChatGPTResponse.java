package com.example.pantrypal_backend.external.openAI.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatGPTResponse(
        @JsonProperty("choices") ChatGPTResponseChoice[] chatGPTResponseChoiceArr
) {
}

