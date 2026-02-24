package dev.matheus.ecommerce.Mapper;

import dev.matheus.ecommerce.DTO.ProdutoResponseDTO;
import dev.matheus.ecommerce.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoResponseDTO convertToResponseToDto(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNomeProduto(),
            produto.getDescricao(),
            produto.getPreco(),
            produto.getQuantidade()
        );
    }
}
