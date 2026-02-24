package dev.matheus.ecommerce.Service;

import dev.matheus.ecommerce.DTO.EstoqueRequestDTO;
import dev.matheus.ecommerce.DTO.EstoqueResponseDTO;
import dev.matheus.ecommerce.Exceptions.BusinessException;
import dev.matheus.ecommerce.Exceptions.ResourceNotFoundException;
import dev.matheus.ecommerce.Mapper.EstoqueMapper;
import dev.matheus.ecommerce.Model.Estoque;
import dev.matheus.ecommerce.Model.Produto;
import dev.matheus.ecommerce.Repository.EstoqueRepository;
import dev.matheus.ecommerce.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final EstoqueMapper mapper;

    public EstoqueService(EstoqueRepository repository, ProdutoRepository produtoRepository, ProdutoService produtoService, EstoqueMapper mapper) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
        this.mapper = mapper;
    }


    // Listar estoque
    public List<EstoqueResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }


    // Buscar estoque por ID
    public EstoqueResponseDTO findById(Long movementId){
        return repository.findById(movementId)
                .map(mapper::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Movimento de estoque não encontrado com o ID: " + movementId));
    }


    //Criar movimento de estoque
    @Transactional
    public EstoqueResponseDTO create(EstoqueRequestDTO dto){
        Produto produto = produtoService.findById(dto.produtoId());

        switch (dto.movementType()){
            case COMPRA -> addStock(produto, dto.quantidade());
            case VENDA -> removeStock(produto, dto.quantidade());
        }

        Estoque movimento = new Estoque();
        movimento.setProduto(produto);
        movimento.setMovementType(dto.movementType());
        movimento.setQuantidade(dto.quantidade());
        movimento.setCriadoEm(LocalDateTime.now());

        Estoque save = repository.save(movimento);
        return mapper.convertToDto(save);

    }


    // Metodo para adicionar estoque
    private void addStock(Produto produto, Integer quantidade) {
        if(quantidade <= 0){
            throw new BusinessException("A quantidade deve ser maior que zero.");
        }
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        produtoRepository.save(produto);
    }


    // Metodo para remover estoque
    private void removeStock(Produto produto, Integer quantidade) {
        if(quantidade <=0){
            throw new BusinessException("A quantidade deve ser maior que zero.");
        }
        if(produto.getQuantidade() < quantidade) {
            throw new BusinessException("Estoque insuficiente ");
        }
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepository.save(produto);
    }
}

