package Projeto.repositorio;

import Projeto.entidades.Endereço;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndereçoRepositorio extends JpaRepository<Endereço, Long> {

    Endereço findByCep(String cep);
}
