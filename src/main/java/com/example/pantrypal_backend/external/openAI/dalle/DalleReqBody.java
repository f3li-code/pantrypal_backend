package com.example.pantrypal_backend.external.openAI.dalle;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DalleReqBody(
        @JsonProperty String prompt,
        @JsonProperty String model,
        @JsonProperty String size,
        @JsonProperty int n


) {
    public DalleReqBody(String prompt, String model, String size, int n) {
        this.prompt = prompt;
        this.model = model;
        this.size = size;
        this.n = n;
    }
    public DalleReqBody(String prompt)  {
        this(prompt, "dall-e-2", "256x256", 1);
    }
}
