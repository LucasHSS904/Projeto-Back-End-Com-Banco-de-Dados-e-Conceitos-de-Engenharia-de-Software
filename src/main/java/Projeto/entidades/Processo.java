package Projeto.entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private Set<Parte> partes;

    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
}
