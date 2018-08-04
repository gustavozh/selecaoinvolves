package com.involves.selecao.alerta;

public enum MensagemDeAlerta {

	RUPTURA("Ruptura detectada!"),
	PARTICIPACAO_SUPERIOR("Participação superior ao estipulado!"),
	PARTICIPACAO_INFERIOR("Participação inferior ao estipulado!"),
	PRECO_ACIMA("Preço acima do estipulado!"),
	PRECO_ABAIXO("Preço abaixo do estipulado!");
	
	private String mensagem;
	
	MensagemDeAlerta(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
}
