package br.projecao.procura.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Atividade implements Serializable{

	private static final long serialVersionUID = 1804758934758934734L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(nullable=false)
	private String nome;
	
	@Enumerated( EnumType.STRING)
	@Column(nullable=false)
	private TipoAtividade tipo;
	
	@Column(nullable=true)
	private Date dataFinal;
	
	@Column(nullable=true)
	private Date dataEntrega;
	
	@Column(nullable=true)
	private Date dataApresentacao;
	
	@Column(nullable=true)
	private Date dataExercicio;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="atividade")
	private Set<AtividadeUsuario> usuariosAtividade = new HashSet<AtividadeUsuario>(0);
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoAtividade getTipo() {
		return tipo;
	}
	public void setTipo(TipoAtividade tipo) {
		this.tipo = tipo;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Date getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	public Date getDataExercicio() {
		return dataExercicio;
	}
	public void setDataExercicio(Date dataExercicio) {
		this.dataExercicio = dataExercicio;
	}
	
	public Set<AtividadeUsuario> getUsuariosAtividade() {
		return usuariosAtividade;
	}
	public void setUsuarioAtividade(Set<AtividadeUsuario> usuariosAtividade) {
		this.usuariosAtividade = usuariosAtividade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
