package br.projecao.procura.persistence.dao;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.persistence.entity.AtividadeUsuario;
import br.projecao.procura.persistence.generic.Dao;

public class AtividadeUsuarioDao extends Dao<AtividadeUsuario>{

	public AtividadeUsuarioDao() {
		super(AtividadeUsuario.class);
	}

	
	public void remove(int idUsuario, int idAtividade){
		EntityTransaction tr = manager.getTransaction();
		try{
			tr.begin();
			Query q = manager.createQuery("delete from AtividadeUsuario where usuario_id="+idUsuario+" and atividade_id="+idAtividade);
			q.executeUpdate();
			manager.flush();
			tr.commit();
		}catch(Exception e){
			AMBean.sendMsg("Exception no DAO", String.format("Falha ao remover entidade (%s) msg:%s , Classe:%s",entityClass.getName(),e.getMessage(),getClass().getSimpleName()));
			tr.rollback();
		}
	}
	
	public void remove(int idAtividade){
		EntityTransaction tr = manager.getTransaction();
		try{
			tr.begin();
			Query q = manager.createQuery("delete from AtividadeUsuario where atividade_id="+idAtividade);
			q.executeUpdate();
			manager.flush();
			tr.commit();
		}catch(Exception e){
			AMBean.sendMsg("Exception no DAO", String.format("Falha ao remover entidade (%s) msg:%s , Classe:%s",entityClass.getName(),e.getMessage(),getClass().getSimpleName()));
			tr.rollback();
		}
	}
}
