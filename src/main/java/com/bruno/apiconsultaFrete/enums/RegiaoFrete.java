package com.bruno.apiconsultaFrete.enums;

public enum RegiaoFrete {

	SUDESTE("Sudeste", 7.85),
	CENTRO_OESTE("Centro-Oeste", 12.50),
	NORDESTE("Nordeste", 15.98), 
	SUL("Sul", 17.30),
	NORTE("Norte", 20.83);

	private final String nome;
	private final double valor;

	RegiaoFrete(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public double getValor() {
		return valor;
	}
}
