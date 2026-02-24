package dev.matheus.ecommerce.Controller;

import dev.matheus.ecommerce.DTO.UserResponseDTO;
import dev.matheus.ecommerce.Mapper.UserMapper;
import dev.matheus.ecommerce.Model.User;
import dev.matheus.ecommerce.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok(users.stream()
                .map(mapper::convertToResponseToDto)
                .collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok(mapper.convertToResponseToDto(user));
    }
}
