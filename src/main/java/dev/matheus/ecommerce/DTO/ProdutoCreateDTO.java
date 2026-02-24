package dev.matheus.ecommerce.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoCreateDTO (
        @NotBlank String nomeProduto,
        String descricao,
        @NotNull @Positive BigDecimal preco,
        @NotNull Integer quantidade
){
}
