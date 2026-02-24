package dev.matheus.ecommerce.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record PedidoCreateDTO(
        @NotEmpty List<PedidoItemDTO> items
    ) {
         public record PedidoItemDTO(
            @NotNull Long produtoId,
            @NotNull @Positive Integer quantidade
    ) {
    }
}
