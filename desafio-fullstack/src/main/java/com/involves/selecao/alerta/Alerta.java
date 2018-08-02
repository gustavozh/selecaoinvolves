package com.involves.selecao.alerta;

public class Alerta {
	
	private String pontoDeVenda;
	private String descricao;
	private String objetoDeAnalise;
	private Integer flTipo;
	private Integer margem;
	
	public String getPontoDeVenda() {
		return pontoDeVenda;
	}
	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObjetoDeAnalise() {
		return objetoDeAnalise;
	}
	public void setObjetoDeAnalise(String objetoDeAnalise) {
		this.objetoDeAnalise = objetoDeAnalise;
	}
	public Integer getFlTipo() {
		return flTipo;
	}
	public void setFlTipo(Integer flTipo) {
		this.flTipo = flTipo;
	}
	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}
}
