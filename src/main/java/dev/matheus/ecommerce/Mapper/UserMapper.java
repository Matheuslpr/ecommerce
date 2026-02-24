package dev.matheus.ecommerce.Mapper;

import dev.matheus.ecommerce.DTO.UserResponseDTO;
import dev.matheus.ecommerce.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO convertToResponseToDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
