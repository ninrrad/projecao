package br.projecao.procura.beans;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.persistence.dao.ProfessorDao;
import br.projecao.procura.persistence.entity.Professor;

@Named
@ViewScoped
public class ProfessorBean extends AMBean<Professor> {
   private static final long serialVersionUID = 7933799063804626082L;
	
   @Inject
	ProfessorDao professorDao;
	
	@Override
	protected ProfessorDao  getBo() {
		return professorDao;
	}

}
