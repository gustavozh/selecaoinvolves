package com.involves.selecao.alerta;

public enum Perguntas {

	SHARE("%Share"),
	QUAL_SITUACAO_PRODUTO("Qual a situação do produto?"),
	QUAL_PRECO_PRODUTO("Qual o preço do produto?");
	
	private String pergunta;
	
	Perguntas(String pergunta){
		this.pergunta = pergunta;
	}
	
	public String getPergunta() {
		return pergunta;
	}
}
