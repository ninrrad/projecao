package br.projecao.procura.persistence.entity;

public enum TipoAtividade {
	
	TRABALHO(false,true,false,false),
	TRABALHO_AVALIDADO(true,true,false,false),
	TRABALHO_APRESENTADO(false,true,true,false),
	TRABALHO_APRESENTADO_AVALIDADO(true,true,true,false),
	EXERCICIO(false,false,false,false),
	EXERCICIO_AVALIADO(true,true,false,false),
	PROVA(true,true,false,false),
	ATIVIDADE_COMPLEMENTAR(false,false,false,true),
	VOLUNTARIO(false,true,false,true),
	MONITORIA(false,true,false,true);
	
	boolean avaliacao;
	boolean entrega;
	boolean apresentacao;
	boolean complementar;
	
	private TipoAtividade(boolean avaliacao, boolean entrega, boolean apresentacao, boolean complementar) {
		this.avaliacao = avaliacao;
		this.entrega = entrega;
		this.apresentacao = apresentacao;
		this.complementar = complementar;
	}
	
	public Boolean isEntrega() {
		return entrega;
	}
	public Boolean isApresentacao() {
		return apresentacao;
	}
	public boolean isAvaliacao() {
		return avaliacao;
	}
	public boolean isComplementar() {
		return complementar;
	}
}
