package br.projecao.procura.persistence.generic;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Classe generica que deverá ser extendida por todos os DAOs, sendo necessario um dao por entidade para persistir a mesma.
 * É responssavel por persistir as entidades no banco usando JPA,   
 * atuando como Model, no modelo MVC
 */
public abstract class Dao<E> {
	
	public  static final String DEFAULT_SELECT =" select e from  %s e ";
	
	/* Será injetado automaticamete durante a criação dessa classe e é a classe 
	 * JPA responssavel por persistir e recuperar as  etidades no banco */
	@Inject 
	protected EntityManager manager;
	
	/* Classe da entidade necessária para efetuar consultas no banco  */
	protected Class<E> entityClass;
	
	protected Dao(Class<E> cl){
		entityClass = cl;
	}
	
	public E getByID(int id){
		return manager.find(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> listar(){
		return manager.createQuery(String.format(DEFAULT_SELECT,entityClass.getName())).getResultList();
	}
	
	public void remove(E entidade){
		manager.remove(entidade);
	}
	
	public void salvar(E entidade){
		manager.persist(entidade);
	}
	
	public void atualizar(E entidade){
		manager.merge(entidade);
	}
	
	
	
}
