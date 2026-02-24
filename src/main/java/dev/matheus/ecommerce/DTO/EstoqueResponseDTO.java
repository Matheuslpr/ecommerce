package dev.matheus.ecommerce.DTO;

import dev.matheus.ecommerce.Enums.MovementType;

public record EstoqueResponseDTO (
        Long produtoId,
        Long movementId,
        MovementType movementType,
        Integer quantidade
){
}
