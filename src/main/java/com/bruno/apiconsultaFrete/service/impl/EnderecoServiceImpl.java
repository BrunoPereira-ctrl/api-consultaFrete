package com.bruno.apiconsultaFrete.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bruno.apiconsultaFrete.dto.EnderecoFreteDTO;
import com.bruno.apiconsultaFrete.enums.RegiaoFrete;
import com.bruno.apiconsultaFrete.models.Endereco;
import com.bruno.apiconsultaFrete.service.EnderecoService;
import com.bruno.apiconsultaFrete.service.exceptions.InvalidResourceException;
import com.bruno.apiconsultaFrete.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private static final String URL_VIA_CEP = "https://viacep.com.br/ws/{cep}/json/";
    
    public Endereco consultaEndereco(String cep) {
        cep = cep.replaceAll("\\D", "");

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Endereco> enderecoResponse = restTemplate.getForEntity(URL_VIA_CEP, Endereco.class, cep);

            return enderecoResponse.getBody();
        } catch (HttpClientErrorException e) {
            throw new InvalidResourceException("CEP inválido " + cep);
        }
    }
    
    public EnderecoFreteDTO consultaEnderecoComFrete(String cep) {
    	cep = cep.replaceAll("\\D", "");
        Endereco endereco = consultaEndereco(cep);
        
        try {
            double frete = getFretePorEstado(endereco.getUf());
            return new EnderecoFreteDTO(endereco, frete);
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Endereço não encontrado para o CEP " + cep);
        }
    }
    
    private double getFretePorEstado(String estado) {
        switch (estado.toUpperCase()) {
        case "SP":
        case "RJ":
        case "ES":
        case "MG":
        return RegiaoFrete.SUDESTE.getValor();
        case "DF":
        case "GO":
        case "MT":
        case "MS":
        return RegiaoFrete.CENTRO_OESTE.getValor();
        case "AL":
        case "BA":
        case "CE":
        case "MA":
        case "PB":
        case "PE":
        case "PI":
        case "RN":
        case "SE":
        return RegiaoFrete.NORDESTE.getValor();
        case "PR":
        case "RS":
        case "SC":
        return RegiaoFrete.SUL.getValor();
        case "AC":
        case "AM":
        case "AP":
        case "PA":
        case "RO":
        case "RR":
        case "TO":
        return RegiaoFrete.NORTE.getValor();
        default:
        throw new IllegalArgumentException("Estado não encontrado: " + estado);
        }
    }
}