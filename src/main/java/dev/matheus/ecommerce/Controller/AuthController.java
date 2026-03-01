package dev.matheus.ecommerce.Controller;

import dev.matheus.ecommerce.DTO.LoginRequestDTO;
import dev.matheus.ecommerce.DTO.LoginResponseDTO;
import dev.matheus.ecommerce.DTO.RegisterDTO;
import dev.matheus.ecommerce.Model.User;
import dev.matheus.ecommerce.Repository.UserRepository;
import dev.matheus.ecommerce.Security.JwtUtil;
import dev.matheus.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private final UserService service;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthController(UserService service, UserRepository repository, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto){
        if(this.repository.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().body("E-mail já está em uso, tente novamente.");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());

        User newUser = new User(null, dto.username(), dto.email(), encryptedPassword);
        this.repository.save(newUser);
        return ResponseEntity.ok().body("Usuário registrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        String token = JwtUtil.generateToken((user.getUsername()));

        return ResponseEntity.ok(new LoginResponseDTO(token));

        }
}
