package com.example.pantrypal_backend.external.openAI.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatGPTResponseChoice(
        @JsonProperty("text") String text

) {
}
