package com.example.pantrypal_backend.external.openAI.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatReqBody(
        @JsonProperty("model") String model,
        @JsonProperty("max_tokens") int maxToken,
        @JsonProperty("temperature") double temperature,
        @JsonProperty("prompt") String prompt
) {
    public ChatReqBody(String model, int maxToken, double temperature, String prompt) {
        this.model = model;
        this.maxToken = maxToken;
        this.temperature = temperature;
        this.prompt = prompt;
    }
    public ChatReqBody(String prompt) {
        this("text-davinci-003", 300, 1.0, prompt);
    }
}
