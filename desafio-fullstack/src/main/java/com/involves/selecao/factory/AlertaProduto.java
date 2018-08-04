package com.involves.selecao.factory;

import java.util.ArrayList;
import java.util.List;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Perguntas;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.Respostas;
import com.involves.selecao.alerta.MensagemDeAlerta;

public class AlertaProduto implements IAlertaCreator{
	
	private Pesquisa pesquisa;

	protected AlertaProduto(Pesquisa pesquisa) {
		super();
		this.pesquisa = pesquisa;
	}

	@Override
	public List<Alerta> getAlertas() {
		List<Alerta> alertas = new ArrayList<Alerta>();
		for (int index = 0; index < pesquisa.getRespostas().size(); index++){
			Alerta alerta = null;
			Resposta questao = pesquisa.getRespostas().get(index);
			if (questao.getPergunta().equals(Perguntas.QUAL_SITUACAO_PRODUTO.getPergunta())
					&& questao.getResposta().equals(Respostas.PRODUTO_AUSENTE.getResposta())) {
				alerta = new Alerta();
				alerta.setDescricao(MensagemDeAlerta.RUPTURA.getMensagem());
				alerta.setTipo(1);
				alertas.add(alerta);
			} else if(questao.getPergunta().equals(Perguntas.QUAL_PRECO_PRODUTO.getPergunta())) {
				int precoColetado = Integer.parseInt(questao.getResposta());
				int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
				if (precoColetado == precoEstipulado) {
					continue;
				} else if(precoColetado > precoEstipulado){
					alerta = new Alerta();
				    alerta.setDescricao(MensagemDeAlerta.PRECO_ACIMA.getMensagem());
				    alerta.setTipo(2);
				} else if(precoColetado < precoEstipulado){
					alerta = new Alerta();
				    alerta.setDescricao(MensagemDeAlerta.PRECO_ABAIXO.getMensagem());
				    alerta.setTipo(3);
				}
				alerta.setMargem(precoEstipulado - precoColetado);
				alertas.add(alerta);
			} else {
				continue;
			}
			alerta.setObjetoDeAnalise(pesquisa.getProduto());
			alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		} 
		return alertas;
	}

}
