package br.projecao.procura.persistence.dao;

import br.projecao.procura.persistence.entity.Professor;
import br.projecao.procura.persistence.generic.Dao;

public class ProfessorDao extends Dao<Professor>{

	public ProfessorDao() {
		super(Professor.class);
	}

}
