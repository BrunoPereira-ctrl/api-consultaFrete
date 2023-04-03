# API REST - Consulta de endereço e cálculo de frete

Este projeto é uma API REST que realiza consultas de endereço a partir de um CEP e calcula o frete para a região correspondente ao CEP informado.

O contrato da API é conforme especificado abaixo:

- Endpoint: POST v1/consulta-endereco
- Request:

```json
{
    "cep": "01001000"
}
```

- Response HTTP 200:

```json
{
    "cep": "01001-000",
    "rua": "Praça da Sé",
    "complemento": "lado ímpar",
    "bairro": "Sé",
    "cidade": "São Paulo",
    "estado": "SP",
    "frete": 7.85
}
```

Para a busca do endereço do CEP, é realizada uma consulta à API VIA CEP, conforme a documentação em https://viacep.com.br/. O valor do frete é fixo de acordo com as regiões do Brasil:

- Sudeste (R$ 7,85)
- Centro-Oeste (R$ 12,50)
- Nordeste (R$ 15,98)
- Sul (R$ 17,30)
- Norte (R$ 20,83)

O CEP é obrigatório e pode ser passado com ou sem máscara na entrada. Se o CEP não for encontrado ou esteja fora do padrão com oito dígitos, uma mensagem informativa será retornada para o cliente.

# Documentação Swagger
A documentação da API está disponível em http://localhost:8080/swagger-ui.html.
