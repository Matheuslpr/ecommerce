package dev.matheus.ecommerce.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
