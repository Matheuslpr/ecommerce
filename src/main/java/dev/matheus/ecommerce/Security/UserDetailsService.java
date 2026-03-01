package dev.matheus.ecommerce.Security;

import dev.matheus.ecommerce.Exceptions.ResourceNotFoundException;
import dev.matheus.ecommerce.Repository.UserRepository;
import io.micrometer.common.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

   private final  UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email){
        UserDetails user = userRepository.findByEmail(email);
        if(user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }
        return user;
    }
}