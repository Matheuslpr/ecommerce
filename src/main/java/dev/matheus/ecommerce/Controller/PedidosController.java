package dev.matheus.ecommerce.Controller;

import dev.matheus.ecommerce.DTO.PedidoCreateDTO;
import dev.matheus.ecommerce.DTO.PedidoResponseDTO;
import dev.matheus.ecommerce.Mapper.PedidoMapper;
import dev.matheus.ecommerce.Model.Pedido;
import dev.matheus.ecommerce.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

private final PedidoService service;
private final PedidoMapper mapper;

    public PedidosController(PedidoService service, PedidoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@RequestBody @Valid PedidoCreateDTO dto){
        Pedido pedido = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertToDto(pedido));
    }
}
