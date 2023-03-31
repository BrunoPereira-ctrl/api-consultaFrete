package com.bruno.apiconsultaFrete.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bruno.apiconsultaFrete.enums.RegiaoFrete;
import com.bruno.apiconsultaFrete.models.Endereco;
import com.bruno.apiconsultaFrete.models.EnderecoFrete;
import com.bruno.apiconsultaFrete.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

    private static final String URL_VIA_CEP = "https://viacep.com.br/ws/{cep}/json/";

	public ResponseEntity<EnderecoFrete> consultaEndereco(String cep) {
	
		cep = cep.replaceAll("\\D","");

		try {
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<Endereco> enderecoResponse = restTemplate.getForEntity(URL_VIA_CEP,
					Endereco.class, cep);

			Endereco endereco = enderecoResponse.getBody();

			double frete = getFretePorEstado(endereco.getUf());

			EnderecoFrete enderecoFrete = new EnderecoFrete(endereco.getCep(), endereco.getLogradouro(),
					endereco.getComplemento(), endereco.getBairro(), endereco.getLocalidade(),
					endereco.getUf(), frete);

			return ResponseEntity.ok(enderecoFrete);
		} catch (HttpClientErrorException e) {
			throw new ResourceNotFoundException("CEP inválido " + cep);
		}
		catch (NullPointerException e) {
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
