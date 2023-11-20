package Projeto.serviços.processo;

import Projeto.controles.processo.ProcessoRequisiçao;
import Projeto.controles.processo.ProcessoResposta;

import java.util.Set;

public interface ProcessoServiço {

    Set<ProcessoResposta> list();

    ProcessoResposta getById(Long id);

    ProcessoResposta create(ProcessoRequisiçao request);

    ProcessoResposta update(Long id, ProcessoRequisiçao request);

    ProcessoResposta delete(Long id);

    ProcessoResposta addPartes(Long id, Set<Long> parteIds);
}
