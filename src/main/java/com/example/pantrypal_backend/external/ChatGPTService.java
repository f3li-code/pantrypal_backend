package com.example.pantrypal_backend.external;

import com.example.pantrypal_backend.external.openAI.chat.ChatReqBody;
import com.example.pantrypal_backend.external.openAI.dalle.DalleReqBody;
import com.example.pantrypal_backend.external.openAI.dalle.DalleResponse;
import com.example.pantrypal_backend.model.Recipe;
import com.example.pantrypal_backend.model.RecipeBuilder;
import com.example.pantrypal_backend.external.openAI.RecipeFrontReqBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@Service
public class ChatGPTService {


    private final OpenAiClient openAiClient;
    private final Logger logger = LoggerFactory.getLogger(ChatGPTService.class);

    public ChatGPTService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public Recipe getChatResponse(RecipeFrontReqBody reqBody) {
        if (!reqBody.isReal()) return createFakeRecipe();
        String prompt = createChatGPTPrompt(reqBody.mealType(), reqBody.ingredients());
        ChatReqBody body = new ChatReqBody(prompt);
        String chatGPTResponseText = openAiClient.getChatGPTResponse(body)
                                                .chatGPTResponseChoiceArr()[0]
                                                .text();
        logger.info(chatGPTResponseText);
        JSONObject recipeComponents = new JSONObject(chatGPTResponseText);

        String imgBase64Str = null;
        if (reqBody.withImage()) {
            imgBase64Str = getDalleImgBase64Str(recipeComponents.getString("title"));
        }
        return new RecipeBuilder().setTitle(recipeComponents.getString("title"))
                .setMealType(reqBody.mealType())
                .setIngredients(recipeComponents.getString("ingredients"))
                .setInstructions(recipeComponents.getString("instructions"))
                .setImageBase64Str(imgBase64Str)
                .build();
    }

    private String getDalleImgBase64Str(String prompt) {
        DalleResponse dalleResponse = openAiClient.getDalleImage(new DalleReqBody(prompt));
        String url = dalleResponse.data()[0].url();
        String imgInBase64Str = null;
        InputStream in = null;
        try {
            in = new URI(url).toURL().openStream();
            imgInBase64Str = Base64
                    .getEncoder()
                    .encodeToString(in.readAllBytes());
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return imgInBase64Str;
    }

    private String createChatGPTPrompt(String mealType, String ingredients) {
        String template = "Give me a simple %s recipe with the ingredients %s " +
                "in the form of a json object " +
                "{title: String, ingredients: String with \\n chars, instructions: String with \\n chars}";
        return template.formatted(mealType, ingredients);
    }

    private Recipe createFakeRecipe() {
        return new RecipeBuilder().setTitle("Fake Recipe")
                .setIngredients("Ingredients 1\nIngredients 2")
                .setInstructions("Instruction 1\nInstruction 2\nInstruction 3\nInstruction 4")
                .setMealType("Dinner")
                .build();
    }
}
