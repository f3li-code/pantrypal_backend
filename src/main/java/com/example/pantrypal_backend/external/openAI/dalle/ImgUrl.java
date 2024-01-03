package com.example.pantrypal_backend.external.openAI.dalle;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImgUrl(
        @JsonProperty String url
) {
}
