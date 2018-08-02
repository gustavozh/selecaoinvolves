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
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		Pesquisa[] ps = getPesquisas();//TODO Deal with exceptions
		for (int i = 0; i < ps.length; i++){
			for (int j = 0; j < ps[i].getRespostas().size(); j++){
				Resposta resposta = ps[i].getRespostas().get(j);
				if (resposta.getPergunta().equals("Qual a situa��o do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
					    Alerta alerta = new Alerta();
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setDescricao("Ruptura detectada!");
					    alerta.setObjetoDeAnalise(ps[i].getProduto());
					    alerta.setFlTipo(1);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o pre�o do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
					if(precoColetado > precoEstipulado){
					    Alerta alerta = new Alerta();
					    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Pre�o acima do estipulado!");
					    alerta.setObjetoDeAnalise(ps[i].getProduto());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(2);
					    gateway.salvar(alerta);
					} else if(precoColetado < precoEstipulado){
						Alerta alerta = new Alerta();
					    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Pre�o abaixo do estipulado!");
					    alerta.setObjetoDeAnalise(ps[i].getProduto());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(3);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("%Share")) {
					int participacaoEstipulada = Integer.parseInt(ps[i].getParticipacao_estipulada());
					int participacaoColetada = Integer.parseInt(resposta.getResposta());
					if(participacaoColetada > participacaoEstipulada){
					    Alerta alerta = new Alerta();
					    int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Participa��o superior ao estipulado!");
					    alerta.setObjetoDeAnalise(ps[i].getCategoria());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(2);
					    gateway.salvar(alerta);
					} else if(participacaoColetada < participacaoEstipulada){
						Alerta alerta = new Alerta();
					    int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
					    alerta.setMargem(margem);
					    alerta.setDescricao("Participa��o inferior ao estipulado!");
					    alerta.setObjetoDeAnalise(ps[i].getCategoria());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(3);
					    gateway.salvar(alerta);
					}
				} else {
					System.out.println("Alerta ainda n�o implementado!");
				}
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

