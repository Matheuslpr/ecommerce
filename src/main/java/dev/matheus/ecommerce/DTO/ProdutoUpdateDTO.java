package dev.matheus.ecommerce.DTO;

import java.math.BigDecimal;

public record ProdutoUpdateDTO(
        String nomeProduto,
        String descricao,
        BigDecimal preco,
        Integer quantidade
) {
}
