package dev.matheus.ecommerce.Controller;

import dev.matheus.ecommerce.DTO.EstoqueRequestDTO;
import dev.matheus.ecommerce.DTO.EstoqueResponseDTO;
import dev.matheus.ecommerce.Service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> getAll(){
        List<EstoqueResponseDTO> movimentos = service.findAll();
        return ResponseEntity.ok(movimentos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> create(@RequestBody @Valid EstoqueRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }


}
