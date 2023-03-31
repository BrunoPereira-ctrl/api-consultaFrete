package com.bruno.apiconsultaFrete.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.apiconsultaFrete.models.EnderecoFrete;
import com.bruno.apiconsultaFrete.service.EnderecoService;

@RestController
@RequestMapping("/api/consulta-endereco")
public class EnderecoController {
	
	private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoFrete> consultaEndereco(@RequestBody String cep) {
        return enderecoService.consultaEndereco(cep);
    }
}
