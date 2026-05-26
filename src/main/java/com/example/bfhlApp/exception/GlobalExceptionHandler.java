package com.example.bfhlApp.exception;

import com.example.bfhlApp.dto.BfhlResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * Global exception handler for graceful error handling.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles malformed JSON or unreadable request bodies.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BfhlResponseDTO> handleBadRequest(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse());
    }

    /**
     * Handles all uncaught exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponseDTO> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse());
    }

    private BfhlResponseDTO buildErrorResponse() {
        return BfhlResponseDTO.builder()
                .isSuccess(false)
                .userId("vidhika_anjne_25032005")
                .email("vidhikaanjne230716@acropolis.in")
                .rollNumber("0827CS231294")
                .oddNumbers(Collections.emptyList())
                .evenNumbers(Collections.emptyList())
                .alphabets(Collections.emptyList())
                .specialCharacters(Collections.emptyList())
                .sum("0")
                .concatString("")
                .build();
    }
}
