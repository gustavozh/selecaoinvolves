package com.involves.selecao.factory;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;

public class AlertaProduto implements IAlertaCreator{
	
	private Pesquisa pesquisa;

	protected AlertaProduto(Pesquisa pesquisa) {
		super();
		this.pesquisa = pesquisa;
	}

	@Override
	public Alerta getAlerta() {
		Alerta alerta = null;
		for (int index = 0; index < pesquisa.getRespostas().size(); index++){
			Resposta resposta = pesquisa.getRespostas().get(index);
			if (resposta.getPergunta().equals("Qual a situação do produto?")) {
				if(resposta.getResposta().equals("Produto ausente na gondola")){
					alerta = new Alerta();
				    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				    alerta.setDescricao("Ruptura detectada!");
				    alerta.setObjetoDeAnalise(pesquisa.getProduto());
				    alerta.setFlTipo(1);
				}
			} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
				int precoColetado = Integer.parseInt(resposta.getResposta());
				int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
				if(precoColetado > precoEstipulado){
				    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
				    alerta = new Alerta();
				    alerta.setMargem(margem);
				    alerta.setDescricao("Preço acima do estipulado!");
				    alerta.setObjetoDeAnalise(pesquisa.getProduto());
				    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				    alerta.setFlTipo(2);
				} else if(precoColetado < precoEstipulado){
					int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
					alerta = new Alerta();
				    alerta.setMargem(margem);
				    alerta.setDescricao("Preço abaixo do estipulado!");
				    alerta.setObjetoDeAnalise(pesquisa.getProduto());
				    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				    alerta.setFlTipo(3);
				}
			}
		} 
		return alerta;
	}

}
