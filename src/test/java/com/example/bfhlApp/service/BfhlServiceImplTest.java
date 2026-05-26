package com.example.bfhlApp.service;

import com.example.bfhlApp.dto.BfhlRequestDTO;
import com.example.bfhlApp.dto.BfhlResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BfhlServiceImpl.
 */
class BfhlServiceImplTest {

    private BfhlService bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    @Test
    @DisplayName("Example A: Mixed data with numbers, letters, and special characters")
    void testExampleA() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("vidhika_anjne_25032005", response.getUserId());
        assertEquals("vidhikaanjne230716@acropolis.in", response.getEmail());
        assertEquals("0827CS231294", response.getRollNumber());

        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    @DisplayName("Example B: Multiple numbers and special characters")
    void testExampleB() {
        BfhlRequestDTO request = new BfhlRequestDTO(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    @DisplayName("Example C: Only alphabets with multi-char strings")
    void testExampleC() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.emptyList(), response.getOddNumbers());
        assertEquals(Collections.emptyList(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(Collections.emptyList(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    @DisplayName("Empty data array should return empty results")
    void testEmptyData() {
        BfhlRequestDTO request = new BfhlRequestDTO(Collections.emptyList());
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Null data should be handled gracefully")
    void testNullData() {
        BfhlRequestDTO request = new BfhlRequestDTO(null);
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only numbers should be classified correctly")
    void testOnlyNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("1", "2", "3", "4", "5"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1", "3", "5"), response.getOddNumbers());
        assertEquals(List.of("2", "4"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("15", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only special characters should be classified correctly")
    void testOnlySpecialCharacters() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("@", "#", "$", "%"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("@", "#", "$", "%"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Negative numbers should be handled correctly")
    void testNegativeNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("-3", "-4", "5"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("-3", "5"), response.getOddNumbers());
        assertEquals(List.of("-4"), response.getEvenNumbers());
        assertEquals("-2", response.getSum());
    }

    @Test
    @DisplayName("Response should always contain user details")
    void testUserDetails() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertEquals("vidhika_anjne_26052005", response.getUserId());
        assertEquals("vidhikaanjne230716@acropolis.in", response.getEmail());
        assertEquals("0827CS231294", response.getRollNumber());
    }

    @Test
    @DisplayName("Numbers should be returned as strings")
    void testNumbersAsStrings() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("1", "2"));
        BfhlResponseDTO response = bfhlService.processData(request);

        // Verify numbers are returned as strings
        for (String num : response.getOddNumbers()) {
            assertInstanceOf(String.class, num);
        }
        for (String num : response.getEvenNumbers()) {
            assertInstanceOf(String.class, num);
        }
        assertInstanceOf(String.class, response.getSum());
    }
}
