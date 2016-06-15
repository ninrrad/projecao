package br.projecao.procura.beans;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.beans.generic.ViewScoped;
import br.projecao.procura.persistence.dao.TurmaDao;
import br.projecao.procura.persistence.dao.UsuarioDao;
import br.projecao.procura.persistence.entity.TipoUsuario;
import br.projecao.procura.persistence.entity.Turma;
import br.projecao.procura.persistence.entity.Usuario;

@Named
@ViewScoped
public class TurmaBean extends AMBean<Turma> {
   private static final long serialVersionUID = 6543213213213254685L;
	
   @Inject
	TurmaDao turmaDao;
   @Inject
	UsuarioDao usuarioDao;
	
	@Override
	protected TurmaDao  getBo() {
		return turmaDao;
	}

	public List<Usuario> getProfessores(){
		return usuarioDao.listar(" tipo = '"+ TipoUsuario.PROFESSOR+"'" );
	}
}
