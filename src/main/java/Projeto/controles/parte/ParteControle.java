package Projeto.controles.parte;

import java.util.Set;

public interface ParteControle {

    ParteResposta create(ParteRequisiçao request);

    Set<ParteResposta> list();

    ParteResposta getById(Long id);

    ParteResposta update(Long id, ParteRequisiçao request);

    ParteResposta delete(Long id);
}
