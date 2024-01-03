package com.example.pantrypal_backend.external.openAI.whisper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WhisperResponse (
        @JsonProperty String text
){
}
