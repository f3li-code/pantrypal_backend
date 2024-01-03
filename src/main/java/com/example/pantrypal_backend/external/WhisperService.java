package com.example.pantrypal_backend.external;

import com.example.pantrypal_backend.external.openAI.whisper.EmptyTranslationException;
import com.example.pantrypal_backend.external.openAI.whisper.WhisperResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WhisperService {
    private final OpenAiClient openAiClient;
    private final Logger logger = LoggerFactory.getLogger(WhisperService.class);
    public WhisperService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public WhisperResponse translate(MultipartFile file) throws EmptyTranslationException {
        WhisperResponse whisperResponse = openAiClient.getWhisperTranslation(file, "whisper-1");
        if (whisperResponse.text().length() <= 4) { // lunch should be the shortest string
            throw new EmptyTranslationException();
        }
        return whisperResponse;
    }
}
