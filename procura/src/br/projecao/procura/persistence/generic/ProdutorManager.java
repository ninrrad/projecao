package br.projecao.procura.persistence.generic;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProdutorManager {
	private static EntityManagerFactory JPA_FACTORY = Persistence.createEntityManagerFactory("bd_anm");

	@Produces
	public EntityManager criaEntityManager() {
		return JPA_FACTORY.createEntityManager();
	}
}
