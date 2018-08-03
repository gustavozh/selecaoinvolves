package com.involves.selecao.factory;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;

public class AlertaCategoria implements IAlertaCreator{
	
	private Pesquisa pesquisa;

	protected AlertaCategoria(Pesquisa pesquisa) {
		super();
		this.pesquisa = pesquisa;
	}

	@Override
	public Alerta getAlerta() {
		Alerta alerta = null;
		for (int index = 0; index < pesquisa.getRespostas().size(); index++){
			Resposta resposta = pesquisa.getRespostas().get(index);
			if(resposta.getPergunta().equals("%Share")) {
				int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacao_estipulada());
				int participacaoColetada = Integer.parseInt(resposta.getResposta());
				if(participacaoColetada > participacaoEstipulada){
				    int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
				    alerta = new Alerta();
				    alerta.setMargem(margem);
				    alerta.setDescricao("Participação superior ao estipulado!");
				    alerta.setObjetoDeAnalise(pesquisa.getCategoria());
				    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				    alerta.setFlTipo(2);
				} else if(participacaoColetada < participacaoEstipulada){
					int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
					alerta = new Alerta();
				    alerta.setMargem(margem);
				    alerta.setDescricao("Participação inferior ao estipulado!");
				    alerta.setObjetoDeAnalise(pesquisa.getCategoria());
				    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				    alerta.setFlTipo(3);
				}
			}
		}
		return alerta;
	}

}
