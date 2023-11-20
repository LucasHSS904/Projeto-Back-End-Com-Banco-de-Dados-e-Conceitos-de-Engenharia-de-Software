package Projeto.serviços.parte;

import Projeto.controles.parte.ParteRequisiçao;
import Projeto.controles.parte.ParteResposta;
import Projeto.serviços.endereço.EndereçoServiço;
import Projeto.entidades.Endereço;
import Projeto.entidades.Parte;
import Projeto.mapeamento.ParteMapeamento;
import Projeto.repositorio.ParteRepositorio;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParteServiçoImpl implements ParteServiço {

    private final ParteRepositorio parteRepositorio;

    private final ParteAuxiliar parteAuxiliar;

    private final EndereçoServiço endereçoServiço;

    @Transactional
    public Set<ParteResposta> list() {

        return parteRepositorio.findAll().stream()
                .map((Parte parte) -> ParteMapeamento.toResponse(parte, true))
                .collect(Collectors.toSet());
    }

    @Transactional
    public ParteResposta getById(Long id) {

        Parte parte = parteAuxiliar.returnValidParteById(id);

        return ParteMapeamento.toResponse(parte, true);
    }

    @Transactional
    public ParteResposta create(ParteRequisiçao request) {

        Parte parte = ParteMapeamento.toEntity(request);

        String email = request.getEmail();

        if (!StringUtils.isBlank(email) && !isValidEmail(email)) {
            throw new IllegalArgumentException("Formato de e-mail invalido");
        }

        String cep = request.getCep();

        Endereço endereço = new Endereço();

        if (cep.matches("\\d{8}")) {
            endereço = endereçoServiço.findOrCreateEnderecoBaseCepByCep(cep);
            parte.setEndereço(endereço);
        }

        parteRepositorio.save(parte);

        return ParteMapeamento.toResponse(parte, true);
    }

    @Transactional
    public ParteResposta update(Long id, ParteRequisiçao request) {

        Parte parte = parteAuxiliar.returnValidParteById(id);

        String email = request.getEmail();

        if (!StringUtils.isBlank(email) && !isValidEmail(email)) {
            throw new IllegalArgumentException("Formato de e-mail invalido");
        }

        String cep = request.getCep();

        Endereço endereço = new Endereço();

        if (cep.matches("\\d{8}")) {
            endereço = endereçoServiço.findOrCreateEnderecoBaseCepByCep(cep);
            parte.setEndereço(endereço);
        }


        ParteMapeamento.updateEntity(parte, request);

        parteRepositorio.save(parte);

        return ParteMapeamento.toResponse(parte, true);
    }

    @Transactional
    public ParteResposta delete(Long id) {

        Parte parte = parteAuxiliar.returnValidParteById(id);

        parteRepositorio.deleteById(id);

        return ParteMapeamento.toResponse(parte, true);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
