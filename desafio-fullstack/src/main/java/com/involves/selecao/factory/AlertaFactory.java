package com.involves.selecao.factory;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;

public class AlertaFactory {
	
	private static AlertaFactory instance;
	private Alerta alerta;
	
	private AlertaFactory() {
		
	}
	
	public static AlertaFactory getInstance() {
		if (instance == null) {
			instance = new AlertaFactory();
		}
		return instance;
	}
	
	public Alerta getAlerta() throws Exception {
		return alerta;
	}
	
	public boolean geraAlerta(Pesquisa pesquisa) throws Exception {
		IAlertaCreator alertaCreator;
		switch(pesquisa.getPesquisaType()) {
			case CATEGORIA:
				alertaCreator = new AlertaCategoria(pesquisa);
				break;
			case PRODUTO:
				alertaCreator = new AlertaProduto(pesquisa);
				break;
			default:
				throw new Exception("Pesquisa inválida!"); //TODO Create exception
		}
		alerta = alertaCreator.getAlerta();
		return alerta != null;
	}

}
