package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.factory.AlertaFactory;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		Pesquisa[] pesquisas = getPesquisas();//TODO Deal with exceptions
		
		for (Pesquisa pesquisa : pesquisas) {
			Alerta alerta = null;
			try {
				if (AlertaFactory.getInstance().geraAlerta(pesquisa)) {
					alerta = AlertaFactory.getInstance().getAlerta();
					gateway.salvar(alerta);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	private Pesquisa[] getPesquisas()
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		StringBuffer content = getContent("https://selecao-involves.agilepromoter.com/pesquisas");

		return convertContentToPesquisas(content);
	}

	private Pesquisa[] convertContentToPesquisas(StringBuffer content) {
		Gson gson = new Gson();
		return gson.fromJson(content.toString(), Pesquisa[].class);
	}

	private StringBuffer getContent(String urlString)
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		  new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		return content;
	}
}

