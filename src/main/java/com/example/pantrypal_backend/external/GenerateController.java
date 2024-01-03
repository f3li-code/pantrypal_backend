package com.example.pantrypal_backend.external;

import com.example.pantrypal_backend.external.openAI.whisper.EmptyTranslationException;
import com.example.pantrypal_backend.external.openAI.whisper.WhisperResponse;
import com.example.pantrypal_backend.model.Recipe;
import com.example.pantrypal_backend.external.openAI.RecipeFrontReqBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GenerateController {

    private final ChatGPTService chatGPTService;
    private final WhisperService whisperService;
    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);

    public GenerateController(ChatGPTService chatGPTService, WhisperService whisperService) {
        this.chatGPTService = chatGPTService;
        this.whisperService = whisperService;
    }

    @PostMapping ("/generate")
    public Recipe generateRecipe(@RequestBody RecipeFrontReqBody body) {

        return chatGPTService.getChatResponse(body);
    }

    @PostMapping(value = "/translate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhisperResponse translate(@RequestPart MultipartFile file) throws EmptyTranslationException {
        return whisperService.translate(file);
    }
}
