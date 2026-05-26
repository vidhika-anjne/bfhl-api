package com.example.bfhlApp.service;

import com.example.bfhlApp.dto.BfhlRequestDTO;
import com.example.bfhlApp.dto.BfhlResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of BfhlService that processes input data arrays.
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "vidhika_anjne_25032005";
    private static final String EMAIL = "vidhikaanjne230716@acropolis.in";
    private static final String ROLL_NUMBER = "0827CS231294";

    @Override
    public BfhlResponseDTO processData(BfhlRequestDTO request) {
        List<String> data = request.getData();

        if (data == null) {
            data = Collections.emptyList();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            if (isNumeric(item)) {
                long number = Long.parseLong(item);
                sum += number;
                if (number % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        // Build concatenation string: all alphabetical characters in reverse order
        // with alternating caps (first char uppercase, second lowercase, etc.)
        String concatString = buildConcatString(data);

        return BfhlResponseDTO.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    /**
     * Checks if a string represents a valid integer number.
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string consists entirely of alphabetical characters.
     */
    private boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds the concatenation string by:
     * 1. Collecting all alphabetical characters from each element in the data array
     * 2. Reversing the order of all collected characters
     * 3. Applying alternating caps (first = uppercase, second = lowercase, etc.)
     */
    private String buildConcatString(List<String> data) {
        StringBuilder allChars = new StringBuilder();

        for (String item : data) {
            if (item == null) continue;
            for (char c : item.toCharArray()) {
                if (Character.isLetter(c)) {
                    allChars.append(c);
                }
            }
        }

        // Reverse the collected characters
        String reversed = allChars.reverse().toString();

        // Apply alternating caps: index 0 = uppercase, index 1 = lowercase, etc.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
