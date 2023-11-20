package Projeto.serviços.endereço;

import Projeto.entidades.Endereço;
import Projeto.repositorio.EndereçoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class EndereçoServiçoImpl implements EndereçoServiço {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";
    private final RestTemplate restTemplate;
    private final EndereçoRepositorio endereçoRepositorio;

    @Override
    public Endereço findOrCreateEnderecoBaseCepByCep(String cep) {
        Endereço endereço = endereçoRepositorio.findByCep(cep);

        if (endereço == null) {
            String url = VIA_CEP_URL + cep + "/json";
            ViaResposta viaResposta = restTemplate.getForObject(url, ViaResposta.class);

            assert viaResposta != null;
            if (viaResposta.isErro()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Endereço não encontrado para o CEP: " + cep);
            }

            endereço = new Endereço();
            endereço.setCep(cep);
            endereço.setNomeRua(viaResposta.getLogradouro());
            endereço.setBairro(viaResposta.getBairro());
            endereço.setCidade(viaResposta.getLocalidade());
            endereço.setEstado(viaResposta.getUf());

            endereçoRepositorio.save(endereço);
        }

        return endereço;
    }
}


