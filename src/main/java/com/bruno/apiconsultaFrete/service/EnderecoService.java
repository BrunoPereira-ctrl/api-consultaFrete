package com.bruno.apiconsultaFrete.service;

import com.bruno.apiconsultaFrete.dto.EnderecoFreteDTO;
import com.bruno.apiconsultaFrete.models.Endereco;

public interface EnderecoService {
    Endereco consultaEndereco(String cep);
    EnderecoFreteDTO consultaEnderecoComFrete(String cep);
}
