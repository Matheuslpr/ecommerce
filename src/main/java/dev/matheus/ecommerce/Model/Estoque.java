package dev.matheus.ecommerce.Model;

import dev.matheus.ecommerce.Enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_estoque")

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private Integer quantidade;

    private LocalDateTime criadoEm;
}
