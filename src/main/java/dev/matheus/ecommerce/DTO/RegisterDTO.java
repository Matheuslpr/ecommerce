package dev.matheus.ecommerce.DTO;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password
) {
}
