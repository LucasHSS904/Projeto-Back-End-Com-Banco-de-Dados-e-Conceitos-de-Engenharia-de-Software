package Projeto.serviços.parte;

import Projeto.controles.parte.ParteRequisiçao;
import Projeto.controles.parte.ParteResposta;

import java.util.Set;

public interface ParteServiço {

    Set<ParteResposta> list();

    ParteResposta getById(Long id);

    ParteResposta create(ParteRequisiçao request);

    ParteResposta update(Long id, ParteRequisiçao request);

    ParteResposta delete(Long id);
}
