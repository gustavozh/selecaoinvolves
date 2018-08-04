package com.involves.selecao.alerta;

public enum MensagemDeAlerta {

	RUPTURA("Ruptura detectada!"),
	PARTICIPACAO_SUPERIOR("Participa��o superior ao estipulado!"),
	PARTICIPACAO_INFERIOR("Participa��o inferior ao estipulado!"),
	PRECO_ACIMA("Pre�o acima do estipulado!"),
	PRECO_ABAIXO("Pre�o abaixo do estipulado!");
	
	private String mensagem;
	
	MensagemDeAlerta(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
}
