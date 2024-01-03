package com.example.pantrypal_backend.external.openAI.whisper;

public class EmptyTranslationException extends Exception{
    public EmptyTranslationException() {
        super("Cannot recognize your voice input");
    }
}
