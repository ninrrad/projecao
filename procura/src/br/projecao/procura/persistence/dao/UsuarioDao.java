package br.projecao.procura.persistence.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.projecao.procura.persistence.entity.Usuario;
import br.projecao.procura.persistence.generic.Dao;

public class UsuarioDao extends Dao<Usuario>{

	public Usuario getByLogin(String login){
		TypedQuery<Usuario> q = manager.createQuery("select e from " + Usuario.class.getName() + " e where login ='"+ login +"'",Usuario.class);
		try{
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public UsuarioDao() {
		super(Usuario.class);
	}

}
