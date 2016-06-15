package br.projecao.procura.beans;


import javax.inject.Inject;
import javax.inject.Named;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.beans.generic.ViewScoped;
import br.projecao.procura.persistence.dao.AtividadeDao;
import br.projecao.procura.persistence.entity.Atividade;
import br.projecao.procura.persistence.entity.TipoAtividade;

@Named
@ViewScoped
public class AtividadeBean extends AMBean<Atividade> {
   private static final long serialVersionUID = 6543213213213254685L;
	
   @Inject
	AtividadeDao atividadeDao;
	
	@Override
	protected AtividadeDao  getBo() {
		return atividadeDao;
	}

	public TipoAtividade[] getTipos(){
		return TipoAtividade.values();
	}
}
