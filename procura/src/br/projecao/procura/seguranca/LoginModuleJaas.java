package br.projecao.procura.seguranca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import br.projecao.procura.persistence.dao.UsuarioDao;
import br.projecao.procura.persistence.entity.Usuario;

public class  LoginModuleJaas implements LoginModule{

	  private CallbackHandler handler;
	  private Subject subject;
	  private Usuario userPrincipal;
	  private RolePrincipal rolePrincipal;
	  private String login;
	  private List<String> userGroups;
	  private UsuarioDao dao;
		
	  
	 
	   public UsuarioDao getDao()	    {
	        BeanManager bm = CDI.current().getBeanManager();
	        Bean<UsuarioDao> bean = (Bean<UsuarioDao>) bm.getBeans(UsuarioDao.class).iterator().next();
	        CreationalContext<UsuarioDao> ctx = bm.createCreationalContext(bean);
	        UsuarioDao dao = (UsuarioDao) bm.getReference(bean, UsuarioDao.class, ctx);
	        return dao;
	    }
	  
	  @Override
	  public void initialize(Subject subject,
	      CallbackHandler callbackHandler,
	      Map<String, ?> sharedState,
	      Map<String, ?> options) {

	    handler = callbackHandler;
	    this.subject = subject;
	    //dao = (UsuarioDao) CDI.current().getBeanManager().resolve(CDI.current().getBeanManager().getBeans(UsuarioDao.class));
	    dao = getDao();
	    
	  }

	  @Override
	  public boolean login() throws LoginException {

	    Callback[] callbacks = new Callback[2];
	    callbacks[0] = new NameCallback("login");
	    callbacks[1] = new PasswordCallback("password", true);
	    
	    try {
	    	
	      handler.handle(callbacks);
	      
	      String name = ((NameCallback) callbacks[0]).getName();
	      String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
	      System.out.println(" Cbs:"+((NameCallback) callbacks[0]).getName() +"," +((NameCallback) callbacks[0]).getDefaultName() +" Password:"+password + " login:"+name);
	      Usuario usr = null;
	    		  
	      if(dao !=null) usr =  dao.getByLogin(name);
	     
	      if (usr  !=null  &&
	    	  name != null &&
	          //name.equals(usr.getLogin()) &&
	          password != null && 
	          password.equals(usr.getPassword())) {

	        // We store the username and roles
	        // fetched from the credentials provider
	        // to be used later in commit() method.
	        // For this tutorial we hard coded the
	        // "admin" role
	    	  
	        login = name;
	        userGroups = new ArrayList<String>();
	        userGroups.add("admin");
	        userGroups.add("user");
	        return true;
	      }

	      // If credentials are NOT OK we throw a LoginException
	      throw new LoginException("Falha de Autenticação");

	    } catch (IOException e) {
	      throw new LoginException(e.getMessage());
	    } catch (UnsupportedCallbackException e) {
	      throw new LoginException(e.getMessage());
	    }

	  }

	  @Override
	  public boolean commit() throws LoginException {

	    userPrincipal = new Usuario();
	    subject.getPrincipals().add(userPrincipal);

	    if (userGroups != null && userGroups.size() > 0) {
	      for (String groupName : userGroups) {
	        rolePrincipal = new RolePrincipal(groupName);
	        subject.getPrincipals().add(rolePrincipal);
	      }
	    }

	    return true;
	  }

	  @Override
	  public boolean abort() throws LoginException {
	    return false;
	  }

	  @Override
	  public boolean logout() throws LoginException {
	    subject.getPrincipals().remove(userPrincipal);
	    subject.getPrincipals().remove(rolePrincipal);
	    return true;
	  }

	  
	  
	}