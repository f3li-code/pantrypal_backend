package com.example.pantrypal_backend;

import com.example.pantrypal_backend.external.openAI.whisper.EmptyTranslationException;
import com.example.pantrypal_backend.model.PantryPalErrorResponse;
import com.example.pantrypal_backend.recipe.UpdateRecipeDoesntExistException;
import com.example.pantrypal_backend.user.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
    public class GlobalControllerExceptionHandler {


        Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


        @ExceptionHandler(Exception.class)
        public final ResponseEntity<PantryPalErrorResponse> handleDefaultException(Exception e) {
            logger.error("", e);
            return new ResponseEntity<>(
                    new PantryPalErrorResponse("Unknown error, please try again later.",
                            e.getClass().getName(),
                            e.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }


        @ExceptionHandler(ResponseStatusException.class)
        public final ResponseEntity<PantryPalErrorResponse> handleResponseStatusException(ResponseStatusException e) {
            logger.error("", e.getCause());
            return new ResponseEntity<>(
                    new PantryPalErrorResponse(e.getReason(), e.getCause().getClass().getName(), e.getCause().getMessage()),
                    e.getStatusCode()
            );
        }

        @ExceptionHandler(UpdateRecipeDoesntExistException.class)
        public final ResponseEntity<PantryPalErrorResponse> handleResponseStatusException(UpdateRecipeDoesntExistException e) {
            return new ResponseEntity<>(
                    new PantryPalErrorResponse(e.getMessage(),
                            e.getClass().getName(),
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }

        @ExceptionHandler(UserAlreadyExistException.class)
        public final ResponseEntity<PantryPalErrorResponse> handleResponseStatusException(UserAlreadyExistException e) {
            return new ResponseEntity<>(
                    new PantryPalErrorResponse(e.getMessage(),
                            e.getClass().getName(),
                            null
                    ),
                    HttpStatus.CONFLICT
            );
        }

        @ExceptionHandler(EmptyTranslationException.class)
        public final ResponseEntity<PantryPalErrorResponse> handleResponseStatusException(EmptyTranslationException e) {
            return new ResponseEntity<>(
                    new PantryPalErrorResponse(e.getMessage(),
                            e.getClass().getName(),
                            null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
