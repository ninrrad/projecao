package br.projecao.procura.beans;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.projecao.procura.beans.generic.AMBean;
import br.projecao.procura.beans.generic.ViewScoped;
import br.projecao.procura.persistence.dao.UsuarioDao;
import br.projecao.procura.persistence.entity.TipoUsuario;
import br.projecao.procura.persistence.entity.Usuario;

@Named
@ViewScoped
public class UsuarioBean extends AMBean<Usuario> {
   private static final long serialVersionUID = 938498489323546761L;
	
   @Inject
	UsuarioDao usuarioDao;
    
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

}
