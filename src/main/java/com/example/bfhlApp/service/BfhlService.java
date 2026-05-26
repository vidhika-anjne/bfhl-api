package com.example.bfhlApp.service;

import com.example.bfhlApp.dto.BfhlRequestDTO;
import com.example.bfhlApp.dto.BfhlResponseDTO;

/**
 * Service interface for BFHL data processing.
 */
public interface BfhlService {

    /**
     * Processes the input data array and returns the categorized response.
     *
     * @param request the request containing the data array
     * @return the processed response with categorized data
     */
    BfhlResponseDTO processData(BfhlRequestDTO request);
}
