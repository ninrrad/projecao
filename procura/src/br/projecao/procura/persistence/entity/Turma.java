package br.projecao.procura.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Turma implements Serializable{
	private static final long serialVersionUID = 4854818232051995755L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String diciplina;
	@ManyToOne
	private Usuario professor;
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="turmas")
	private Set<Usuario> alunos;
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="turmas")
	private Set<Atividade> atividades;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDiciplina() {
		return diciplina;
	}
	public void setDiciplina(String diciplina) {
		this.diciplina = diciplina;
	}
	public Usuario getProfessor() {
		return professor;
	}
	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}
	
	public Set<Usuario> getAlunos() {
		return alunos;
	}
	public void setAlunos(Set<Usuario> alunos) {
		this.alunos = alunos;
	}
	

	public Set<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
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
		Turma other = (Turma) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
