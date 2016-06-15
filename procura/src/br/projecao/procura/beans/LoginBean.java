package br.projecao.procura.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.projecao.procura.persistence.dao.UsuarioDao;
import br.projecao.procura.persistence.entity.Usuario;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 6439147054618562443L;

	private boolean autenticado;
	private String  login;
	private String  senha;
	private Usuario usuario;
	
	@Inject private transient UsuarioDao dao;

	public String getLogin() {
		//dao.list();
		return login;
	}
	public void setLogin(String login) {
		
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String logon(){
		
		   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
	        try {
	        	if ( request.getUserPrincipal() == null ) {
	        		  request.getSession(); 
	        	}
	            
	        	if (!autenticado)
	        		request.login(login, senha);
	        	else{
	        		return "/pages/index.jsf?faces-redirect=true";
	        	}
	            usuario = dao.getByLogin(login);
	            autenticado = true;
	            //externalContext.getSessionMap().put(SESSION_USER_VARIABLE_NAME, new User(username));
	            return "/pages/index.jsf?faces-redirect=true";

	        } catch (Exception e) {
	        	autenticado = false;
	            String loginErrorMessage = e.getLocalizedMessage();
	            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(String.format("O usuario %s é invalido!",login)));
	            return "/index.jsf";
	        }
		
		
		/**/
		
	}
	
	boolean getAutenticado(){
	   return autenticado;	
	}
}
