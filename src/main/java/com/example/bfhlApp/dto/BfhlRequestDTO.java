package com.example.bfhlApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request DTO for the /bfhl POST endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BfhlRequestDTO {

    private List<String> data;
}
