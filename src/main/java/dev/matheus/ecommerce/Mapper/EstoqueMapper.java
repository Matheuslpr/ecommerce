package dev.matheus.ecommerce.Mapper;

import dev.matheus.ecommerce.DTO.EstoqueResponseDTO;
import dev.matheus.ecommerce.Model.Estoque;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueResponseDTO convertToDto(Estoque estoque){
        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getMovementType(),
                estoque.getQuantidade()
        );
    }
}
