package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.factory.AlertaFactory;
import com.involves.selecao.factory.FactoryException;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	private static final String URL = "https://selecao-involves.agilepromoter.com/pesquisas";
	
	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws ServiceException {
		Pesquisa[] pesquisas;
		try {
			pesquisas = getPesquisas();
		} catch (IOException e) {
			ServiceException serviceException = new ServiceException("Erro ao buscar pesquisas!");
			serviceException.initCause(e);
			throw serviceException;
		}
		
		for (Pesquisa pesquisa : pesquisas) {
			List<Alerta> alertas = null;
			try {
				if (AlertaFactory.getInstance().geraAlerta(pesquisa)) {
					alertas = AlertaFactory.getInstance().getAlertas();
					alertas.forEach(alerta -> gateway.salvar(alerta));
				}
			} catch (FactoryException factoryException) {
				ServiceException serviceException = new ServiceException("Erro ao criar alertas!");
				serviceException.initCause(factoryException);
				throw serviceException;
			}
		}
	}

	private Pesquisa[] getPesquisas()
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		return convertContentToPesquisas(getContent());
	}

	private Pesquisa[] convertContentToPesquisas(StringBuffer content) {
		return new Gson().fromJson(content.toString(), Pesquisa[].class);
	}

	private StringBuffer getContent()
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = bufferedReader.readLine()) != null) {
		    content.append(inputLine);
		}
		bufferedReader.close();
		return content;
	}
}

