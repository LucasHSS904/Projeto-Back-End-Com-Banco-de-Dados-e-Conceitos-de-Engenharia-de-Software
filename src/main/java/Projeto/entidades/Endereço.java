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
public class Endereço {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String nomeRua;

    private String bairro;

    private String cidade;

    private String estado;

    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "endereço", cascade = CascadeType.ALL)
    private Set<Parte> partes;
}
