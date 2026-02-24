package dev.matheus.ecommerce.DTO;

import dev.matheus.ecommerce.Enums.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record  EstoqueRequestDTO(
        @NotNull @Positive Long produtoId,
        @NotNull MovementType movementType,
        @NotNull Integer quantidade
) {
}
