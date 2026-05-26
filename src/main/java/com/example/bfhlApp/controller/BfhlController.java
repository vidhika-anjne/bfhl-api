package com.example.bfhlApp.controller;

import com.example.bfhlApp.dto.BfhlRequestDTO;
import com.example.bfhlApp.dto.BfhlResponseDTO;
import com.example.bfhlApp.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for the BFHL API.
 */
@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl - Processes the input data array.
     *
     * @param request the request body containing the data array
     * @return 200 OK with the processed response
     */
    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processData(@RequestBody BfhlRequestDTO request) {
        BfhlResponseDTO response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /bfhl - Returns a simple operation code.
     * This can be used for health checks.
     */
    @GetMapping
    public ResponseEntity<OperationCodeResponse> getOperationCode() {
        return ResponseEntity.ok(new OperationCodeResponse(1));
    }

    /**
     * Simple response for the GET endpoint.
     */
    record OperationCodeResponse(int operation_code) {}
}
