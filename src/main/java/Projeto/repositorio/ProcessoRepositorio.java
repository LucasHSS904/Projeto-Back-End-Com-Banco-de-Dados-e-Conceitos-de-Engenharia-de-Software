package Projeto.repositorio;

import Projeto.entidades.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepositorio extends JpaRepository<Processo, Long> {

    boolean existsByNumero(String numero);
}
