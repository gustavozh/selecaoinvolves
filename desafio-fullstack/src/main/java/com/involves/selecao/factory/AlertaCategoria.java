package com.involves.selecao.factory;

import java.util.ArrayList;
import java.util.List;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.MensagemDeAlerta;
import com.involves.selecao.alerta.Perguntas;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;

public class AlertaCategoria implements IAlertaCreator{
	
	private Pesquisa pesquisa;

	protected AlertaCategoria(Pesquisa pesquisa) {
		super();
		this.pesquisa = pesquisa;
	}

	@Override
	public List<Alerta> getAlertas() {
		List<Alerta> alertas = new ArrayList<Alerta>();
		for (int index = 0; index < pesquisa.getRespostas().size(); index++){
			Alerta alerta = null;
			Resposta questao = pesquisa.getRespostas().get(index);
			if(questao.getPergunta().equals(Perguntas.SHARE.getPergunta())) {
				int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacao_estipulada());
				int participacaoColetada = Integer.parseInt(questao.getResposta());
				if (participacaoColetada == participacaoEstipulada) {
					continue;
				} else if(participacaoColetada > participacaoEstipulada){
					alerta = new Alerta();
				    alerta.setDescricao(MensagemDeAlerta.PARTICIPACAO_SUPERIOR.getMensagem());
				    alerta.setTipo(2);
				} else if(participacaoColetada < participacaoEstipulada){
					alerta = new Alerta();
					alerta.setDescricao(MensagemDeAlerta.PARTICIPACAO_INFERIOR.getMensagem());
				    alerta.setTipo(3);
				}
				alerta.setMargem(participacaoEstipulada - participacaoColetada);
				alerta.setObjetoDeAnalise(pesquisa.getCategoria());
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alertas.add(alerta);
			}
		}
		return alertas;
	}

}
