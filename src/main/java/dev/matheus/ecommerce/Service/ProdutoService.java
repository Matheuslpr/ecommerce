package dev.matheus.ecommerce.Service;

import dev.matheus.ecommerce.DTO.ProdutoCreateDTO;
import dev.matheus.ecommerce.DTO.ProdutoUpdateDTO;
import dev.matheus.ecommerce.Exceptions.ResourceNotFoundException;
import dev.matheus.ecommerce.Model.Produto;
import dev.matheus.ecommerce.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    // Listar todos os produtos
    public List<Produto> findAll() {
        return repository.findAll();
    }


    // Buscar produto por ID
    public Produto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }


    // Criar um novo produto
    public Produto create(ProdutoCreateDTO dto) {
        Produto newProduto = new Produto();

        newProduto.setNomeProduto(dto.nomeProduto());
        newProduto.setDescricao(dto.descricao());
        newProduto.setPreco(dto.preco());
        newProduto.setQuantidade(dto.quantidade());

        return repository.save(newProduto);
    }


    //Atualizar um produto existente
    public Produto update(ProdutoUpdateDTO dto, Long id) {
        Produto produtoExistente = findById(id);

        if (dto.nomeProduto() != null && !dto.nomeProduto().isBlank()) produtoExistente.setNomeProduto(dto.nomeProduto());
        if (dto.descricao() != null && !dto.descricao().isBlank()) produtoExistente.setDescricao(dto.descricao());
        if (dto.preco() != null) produtoExistente.setPreco(dto.preco());
        if (dto.quantidade() != null) produtoExistente.setQuantidade(dto.quantidade());

        return repository.save(produtoExistente);
    }


    // Deletar um produto
    public void delete(Long id) {
        Produto produtoExistente = findById(id);
        repository.delete(produtoExistente);
    }
}
