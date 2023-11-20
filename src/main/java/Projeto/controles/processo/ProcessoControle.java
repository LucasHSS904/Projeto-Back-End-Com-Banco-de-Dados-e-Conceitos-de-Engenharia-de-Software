package Projeto.controles.processo;

import java.util.Set;

public interface ProcessoControle {

    ProcessoResposta create(ProcessoRequisiçao request);

    Set<ProcessoResposta> list();

    ProcessoResposta getById(Long id);

    ProcessoResposta update(Long id, ProcessoRequisiçao request);

    ProcessoResposta delete(Long id);

    ProcessoResposta addPartes(Long processoId, Set<Long> parteIds);
}
