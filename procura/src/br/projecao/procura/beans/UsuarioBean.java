package br.projecao.procura.beans;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.beans.generic.ViewScoped;
import br.projecao.procura.persistence.dao.TurmaDao;
import br.projecao.procura.persistence.dao.UsuarioDao;
import br.projecao.procura.persistence.entity.TipoUsuario;
import br.projecao.procura.persistence.entity.Turma;
import br.projecao.procura.persistence.entity.Usuario;

@Named
@ViewScoped
public class UsuarioBean extends AMBean<Usuario> {
   private static final long serialVersionUID = 938498489323546761L;
  
   @Inject
   private UsuarioDao usuarioDao;
   @Inject
   private TurmaDao turmaDao;
    
	@Override
	protected UsuarioDao  getBo() {
		return usuarioDao;
	}
	
	public TipoUsuario[] getTipos(){
		return TipoUsuario.values();
	}
	
	 public DualListModel<Usuario> getSubordinados(){
		 DualListModel<Usuario> subordinados = new DualListModel<Usuario>(); 
		 subordinados.setSource(usuarioDao.listar(entidade.getTipo()== TipoUsuario.PROFESSOR? "tipo = 'ALUNO'":"tipo = 'PROFESSOR'"));
		  List<Usuario> target = (entidade.getSubordinados() !=null) ? new ArrayList<Usuario>(entidade.getSubordinados()) :new ArrayList<Usuario>();
		  subordinados.getSource().removeAll(target);
		  subordinados.getSource().remove(entidade);
		  subordinados.setTarget(target);
	 	  return subordinados;
	 }
	 //TODO: Armazenar lista
	 public void  setSubordinados(DualListModel<Usuario> subordinados){
		 HashSet<Usuario> usuariosSelecionados = new HashSet<Usuario>(subordinados.getTarget());
	 	 entidade.setSubordinados(usuariosSelecionados);
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
		 HashSet<Turma> usuariosSelecionados = new HashSet<Turma>(turmas.getTarget());
	 	 entidade.setTurmas(usuariosSelecionados);
	 	 System.out.println();
	 }
	 
	 @Override
		public String save() {
			if(entidade.getId()==null && !usuarioDao.listar("login ='" + entidade.getLogin() +"'" ).isEmpty()){
				sendMsg("Falha ao salvar", " O login informado já existe, porfavor altere o login.");
				return null;
			}else{
				return super.save();
			}
		}
	 
}
