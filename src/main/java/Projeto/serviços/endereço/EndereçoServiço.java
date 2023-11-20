package Projeto.serviços.endereço;

import Projeto.entidades.Endereço;

public interface EndereçoServiço {

    Endereço findOrCreateEnderecoBaseCepByCep(String cep);
}
