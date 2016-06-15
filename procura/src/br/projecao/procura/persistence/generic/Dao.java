package br.projecao.procura.persistence.generic;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import br.projecao.procura.beans.generic.AMBean;

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
	
	@SuppressWarnings("unchecked")
	public List<E> listar(String filtro){
		return manager.createQuery(String.format(DEFAULT_SELECT+" where "+filtro,entityClass.getName())).getResultList();
	}
	
	public void remove(E entidade){
		EntityTransaction tr = manager.getTransaction();
		try{
			tr.begin();
			manager.remove(entidade);
			manager.flush();
			tr.commit();
		}catch(Exception e){
			AMBean.sendMsg("Exception no DAO", String.format("Falha ao remover entidade (%s) msg:%s , Classe:%s",entityClass.getName(),e.getMessage(),getClass().getSimpleName()));
			tr.rollback();
		}
	}
	
	public void salvar(E entidade){
		EntityTransaction tr = manager.getTransaction();
		try{
			tr.begin();
			manager.persist(entidade);
			manager.flush();
			tr.commit();
		}catch(Exception e){
			AMBean.sendMsg("Exception no DAO", String.format("Falha ao salvar entidade (%s) msg:%s , Classe:%s",entityClass.getName(),e.getMessage(),getClass().getSimpleName()));
			tr.rollback();
		}
	}
	
	public E atualizar(E entidade){
		E ret =null;
		EntityTransaction tr = manager.getTransaction();
		try{
			tr.begin();
			ret = manager.merge(entidade);
			manager.flush();
			tr.commit();
		}catch(Exception e){
			AMBean.sendMsg("Exception no DAO", String.format("Falha ao atualizar entidade (%s) msg:%s , Classe:%s",entityClass.getName(),e.getMessage(),getClass().getSimpleName()));
			tr.rollback();
		}
		return ret;
	}
	
	public E newInstance() throws InstantiationException, IllegalAccessException{
		return entityClass.newInstance();
	}
	
}
