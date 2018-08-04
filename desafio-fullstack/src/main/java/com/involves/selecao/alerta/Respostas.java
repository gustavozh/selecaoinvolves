package com.involves.selecao.alerta;

public enum Respostas {

	PRODUTO_AUSENTE("Produto ausente na gondola");
	
	private String resposta;
	
	Respostas(String resposta) {
		this.resposta = resposta;
	}
	
	public String getResposta() {
		return resposta;
	}
	
}
