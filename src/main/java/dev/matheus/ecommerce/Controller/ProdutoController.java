package dev.matheus.ecommerce.Controller;

import dev.matheus.ecommerce.DTO.ProdutoCreateDTO;
import dev.matheus.ecommerce.DTO.ProdutoResponseDTO;
import dev.matheus.ecommerce.DTO.ProdutoUpdateDTO;
import dev.matheus.ecommerce.Mapper.ProdutoMapper;
import dev.matheus.ecommerce.Model.Produto;
import dev.matheus.ecommerce.Service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        List<ProdutoResponseDTO> produtoDto = service.findAll()
                .stream()
                .map(mapper::convertToResponseToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtoDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok(mapper.convertToResponseToDto(produto));
    }


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody @Valid ProdutoCreateDTO dto){
        Produto produto = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertToResponseToDto(produto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody @Valid ProdutoUpdateDTO dto, @PathVariable Long id) {
        Produto update = service.update(dto,id);
        return ResponseEntity.ok(mapper.convertToResponseToDto(update));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
