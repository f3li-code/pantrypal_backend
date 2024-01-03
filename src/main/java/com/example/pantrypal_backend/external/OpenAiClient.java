package com.example.pantrypal_backend.external;

import com.example.pantrypal_backend.external.openAI.chat.ChatGPTResponse;
import com.example.pantrypal_backend.external.openAI.chat.ChatReqBody;
import com.example.pantrypal_backend.external.openAI.dalle.DalleReqBody;
import com.example.pantrypal_backend.external.openAI.dalle.DalleResponse;
import com.example.pantrypal_backend.external.openAI.whisper.WhisperResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "open-ai-api")
public interface OpenAiClient {
    @PostMapping("/completions")
    ChatGPTResponse getChatGPTResponse(@RequestBody ChatReqBody body);

    @PostMapping(value = "/audio/transcriptions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    WhisperResponse getWhisperTranslation(@RequestPart MultipartFile file, @RequestPart String model);

    @PostMapping("/images/generations")
    DalleResponse getDalleImage(@RequestBody DalleReqBody body);
}
