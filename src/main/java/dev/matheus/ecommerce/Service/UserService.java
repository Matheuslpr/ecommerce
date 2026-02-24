package dev.matheus.ecommerce.Service;

import dev.matheus.ecommerce.Exceptions.ResourceNotFoundException;
import dev.matheus.ecommerce.Model.User;
import dev.matheus.ecommerce.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

   @Transactional
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }

}
