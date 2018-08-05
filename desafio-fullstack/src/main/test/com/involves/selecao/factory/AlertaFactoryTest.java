package com.involves.selecao.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Perguntas;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.Respostas;

public class AlertaFactoryTest {

	private Pesquisa pesquisaProduto;
	private Pesquisa pesquisaCategoria;
	private Pesquisa pesquisaSemTipo;
	
	@Before
	public void setUp() throws Exception {
		Map<Perguntas, String> questoes = new HashMap<Perguntas, String>();
		questoes.put(Perguntas.QUAL_PRECO_PRODUTO, "50");
		questoes.put(Perguntas.QUAL_SITUACAO_PRODUTO, Respostas.PRODUTO_AUSENTE.getResposta());
		pesquisaProduto = CriarPesquisa(1, "João", "Angel One Capoeiras", "48", null, "Ovo de Pascoa Kinder 48", null, "Campanha de pascoa", questoes);
		
		questoes = new HashMap<Perguntas, String>();
		questoes.put(Perguntas.SHARE, "30");
		pesquisaCategoria = CriarPesquisa(1, "Malaquias", "Padaria do Alemão", null, "20", null, "Droga", "Acompanhamento de evolução dos refrigerantes", questoes);
		
		pesquisaSemTipo = CriarPesquisa(1, "Paulo", "Padaria do Alemão", null, null, null, null, "Campanha de pascoa", questoes);
	}

	private Pesquisa CriarPesquisa(int id, String notificante, String pontoDeVenda, String precoEstipulado, 
			String participacaoEstipulada, String produto, String categoria, String rotulo, Map<Perguntas, String> questoes) {
		Pesquisa pesquisa = new Pesquisa();
		pesquisa.setId(id);
		pesquisa.setNotificante(notificante);
		pesquisa.setPonto_de_venda(pontoDeVenda);
		pesquisa.setPreco_estipulado(precoEstipulado);
		pesquisa.setParticipacao_estipulada(participacaoEstipulada);
		pesquisa.setCategoria(categoria);
		pesquisa.setProduto(produto);
		pesquisa.setRotulo(rotulo);
		
		List<Resposta> questionario = new ArrayList<Resposta>();
		questoes.entrySet().forEach(entry ->{
			Resposta questao = new Resposta();
			questao.setPergunta(entry.getKey().getPergunta());
			questao.setResposta(entry.getValue());
			questionario.add(questao);
		});
		pesquisa.setRespostas(questionario);
		
		return pesquisa;
	}

	@Test
	public void testGetAlertas_GeraDoisAlertasProduto() throws FactoryException {
		AlertaFactory.getInstance().geraAlerta(pesquisaProduto);
		List<Alerta> alertas = AlertaFactory.getInstance().getAlertas();
		
		assertNotNull(alertas);
		assertEquals(alertas.size(), 2);
	}
	
	@Test
	public void testGetAlertas_GeraUmAlertaCategoria() throws FactoryException {
		AlertaFactory.getInstance().geraAlerta(pesquisaCategoria);
		List<Alerta> alertas = AlertaFactory.getInstance().getAlertas();
		
		assertNotNull(alertas);
		assertEquals(alertas.size(), 1);
	}
	
	@Test(expected=FactoryException.class)
	public void testGetAlertas_PesquisaTypeInexistente() throws FactoryException {
		AlertaFactory.getInstance().geraAlerta(pesquisaSemTipo);
	}

}
