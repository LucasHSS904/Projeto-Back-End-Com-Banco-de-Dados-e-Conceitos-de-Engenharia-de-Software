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
public class Parte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String documento;

    private String email;

    private String numeroEndereco;

    private String complementoEndereco;

    @ManyToOne()
    private Endereço endereço;

    @ManyToOne()
    private Processo processo;

    @OneToMany(mappedBy = "parte", cascade = CascadeType.ALL)
    private Set<Notificaçao> notificacoes;

    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
}
