package dev.matheus.ecommerce.Mapper;

import dev.matheus.ecommerce.DTO.PedidoResponseDTO;
import dev.matheus.ecommerce.Model.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public PedidoResponseDTO convertToDto(Pedido pedido) {
        var items = pedido.getItems()
                .stream()
                .map(item -> new PedidoResponseDTO.PedidoItemResponseDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNomeProduto(),
                        item.getPrecoUnitario(),
                        item.getQuantidade(),
                        item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                ))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                items,
                pedido.getPrecoTotal()
        );
    }
}