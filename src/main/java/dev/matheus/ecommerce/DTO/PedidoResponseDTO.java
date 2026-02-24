package dev.matheus.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        List<PedidoItemResponseDTO> items,
        BigDecimal precoTotal
) {
    public record PedidoItemResponseDTO(
            Long produtoId,
            String nomeProduto,
            BigDecimal precoUnitario,
            Integer quantidade,
            BigDecimal precoTotal
    ){}
}
