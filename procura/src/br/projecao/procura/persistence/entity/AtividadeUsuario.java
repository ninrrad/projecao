package br.projecao.procura.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AtividadeUsuario implements Serializable {

	private static final long serialVersionUID = -4400034733833691115L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario; 
	
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="atividade_id", nullable=false)
	private Atividade atividade;
	
	@Column(nullable=false)
	private boolean enregue;
	private Date 	dataEntregue;
	private Integer nota;
	private Integer horas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public boolean isEnregue() {
		return enregue;
	}
	public void setEnregue(boolean enregue) {
		this.enregue = enregue;
	}
	public Date getDataEntregue() {
		return dataEntregue;
	}
	public void setDataEntregue(Date dataEntregue) {
		this.dataEntregue = dataEntregue;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeUsuario other = (AtividadeUsuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
