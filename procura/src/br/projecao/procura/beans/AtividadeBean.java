package br.projecao.procura.beans;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.beans.generic.ViewScoped;
import br.projecao.procura.persistence.dao.AtividadeDao;
import br.projecao.procura.persistence.dao.AtividadeUsuarioDao;
import br.projecao.procura.persistence.dao.TurmaDao;
import br.projecao.procura.persistence.entity.Atividade;
import br.projecao.procura.persistence.entity.AtividadeUsuario;
import br.projecao.procura.persistence.entity.TipoAtividade;
import br.projecao.procura.persistence.entity.Turma;
import br.projecao.procura.persistence.entity.Usuario;

@Named
@ViewScoped
public class AtividadeBean extends AMBean<Atividade> {
   private static final long serialVersionUID = 6543213213213254685L;
   
   @Inject
	private AtividadeUsuarioDao atividadeUsuarioDao;
   @Inject
	private AtividadeDao atividadeDao;
   @Inject
   private TurmaDao turmaDao;
   
	@Override
	protected AtividadeDao  getBo() {
		return atividadeDao;
	}

	public TipoAtividade[] getTipos(){
		return TipoAtividade.values();
	}
	
	

	 public DualListModel<Turma> getTurmas(){
		 DualListModel<Turma> turmas = new DualListModel<Turma>(); 
		 turmas.setSource(turmaDao.listar());
		  List<Turma> target = (entidade.getTurmas() !=null) ? new ArrayList<Turma>(entidade.getTurmas()) :new ArrayList<Turma>();
		  turmas.getSource().removeAll(target);
		  turmas.getSource().remove(entidade);
		  turmas.setTarget(target);
	 	  return turmas;
	 }
	 
	 //TODO: Armazenar lista
	 public void  setTurmas(DualListModel<Turma> turmas){
		 HashSet<Turma> turmasSelecionados = new HashSet<Turma>(turmas.getTarget());
		 
		 for(Turma ts : entidade.getTurmas()){
			 for(Usuario u : ts.getAlunos()){
				atividadeUsuarioDao.remove(u.getId(), entidade.getId());
			 }
		 }
		 
		 for(Turma ts : turmasSelecionados){
			 for(Usuario u : ts.getAlunos()){
				AtividadeUsuario atividadeUsuario = new AtividadeUsuario();
				atividadeUsuario.setAtividade(entidade);
				atividadeUsuario.setUsuario(u);
				atividadeUsuarioDao.salvar(atividadeUsuario);
			 }
		 }
		 
	 	 entidade.setTurmas(turmasSelecionados);
	 	 System.out.println();
	 }
}
