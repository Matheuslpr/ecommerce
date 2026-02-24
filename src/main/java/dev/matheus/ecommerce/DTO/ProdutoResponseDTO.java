package dev.matheus.ecommerce.DTO;

import java.math.BigDecimal;

public record ProdutoResponseDTO (
        Long id,
        String nomeProduto,
        String descricao,
        BigDecimal preco,
        Integer quantidade
){
}
