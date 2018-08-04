package com.involves.selecao.factory;

import java.util.List;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.PesquisaType;

public class AlertaFactory {
	
	private static AlertaFactory instance;
	private List<Alerta> alertas;
	
	private AlertaFactory() {
		
	}
	
	public static AlertaFactory getInstance() {
		if (instance == null) {
			instance = new AlertaFactory();
		}
		return instance;
	}
	
	public List<Alerta> getAlertas() {
		return alertas;
	}
	
	public boolean geraAlerta(Pesquisa pesquisa) throws FactoryException {
		IAlertaCreator alertaCreator;
		if (pesquisa.getPesquisaType() == null) {
			throw new FactoryException("Pesquisa inválida! Id:" + pesquisa.getId());
		} else if (pesquisa.getPesquisaType().equals(PesquisaType.CATEGORIA)) {
			alertaCreator = new AlertaCategoria(pesquisa);
		} else if (pesquisa.getPesquisaType().equals(PesquisaType.PRODUTO)) {
			alertaCreator = new AlertaProduto(pesquisa);
		} else {
			throw new FactoryException("Pesquisa inválida! Id:" + pesquisa.getId());
		}
		alertas = alertaCreator.getAlertas();
		return alertas != null && !alertas.isEmpty();
	}

}
