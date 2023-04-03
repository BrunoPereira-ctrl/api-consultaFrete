package com.bruno.apiconsultaFrete.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.apiconsultaFrete.dto.EnderecoFreteDTO;
import com.bruno.apiconsultaFrete.service.EnderecoService;

@RestController
@RequestMapping("v1/consulta-endereco")
public class EnderecoController {
	
	private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoFreteDTO> consultaEndereco(@RequestBody String cep) {
        EnderecoFreteDTO enderecoFrete = enderecoService.consultaEnderecoComFrete(cep);
        return ResponseEntity.ok(enderecoFrete);
    }
}
