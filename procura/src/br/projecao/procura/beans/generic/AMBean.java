package br.projecao.procura.beans.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;

import br.projecao.procura.persistence.entity.Usuario;
import br.projecao.procura.persistence.generic.Dao;

public abstract class AMBean<E> implements Serializable{

	private static final long serialVersionUID = 2422972901879665537L;
	public final String DEFAULT_INDEX="index.jsf";
	
	protected final String boName;
	protected final String paramName;
	protected String indexPage = DEFAULT_INDEX; 
	
	//protected IGenericBo    <E,K> bo;
	protected Integer		 selecionado = 0;
	protected E 			 entidade;

	@Inject
	protected ServletContext context;
	
	protected abstract  <T extends Dao<E>>  T getBo(); 
	
	@SuppressWarnings("unchecked")
	protected AMBean() {
		boName 		= getClass().getSimpleName().toUpperCase() + "_BO";
		paramName 	= getClass().getSimpleName()+ "Entity";
		System.out.println("BoNme " + boName + " param:" + paramName);
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		try {
		
			String param = getRequestParam(paramName);
			System.out.println("Param:"+param);
			if(!param.isEmpty())
				selecionado = new Integer(param);
			
			if (entidade == null && selecionado != null && selecionado > -1) {
					entidade = getBo().getByID(selecionado);
			}
		} catch (Exception e) {
			System.out.println("\n --------------------->Error "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public List<E> getAll() {

		List<E> lista = null;
		try {
			lista = (List<E>) getBo().listar();
		} catch (Exception e) {
			System.out.println("\n --------------------->Error " + e.getMessage());
			e.printStackTrace();
		}
		return lista;
		
	}

	public void remover(E gp) {
		
		System.out.println("Excluir --->" + gp);

		try {
			getBo().remove(gp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Integer getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Integer selecionado) {
		this.selecionado = selecionado;
	}

	public E getEntidade() {
		return entidade;
	}

	public void setEntidade(E entidade) {
		System.out.println("\n------------Setando entidade:" + entidade);
		this.entidade = entidade;

	}

	public String save() {
		try {
			System.out.println("\n Entidade: " + entidade);
			getBo().salvar(entidade);
			
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return indexPage;

	}
/*
	public IGenericBo<E,K> getBo() {
		return bo;
	}

	public void setBo(IGenericBo<E,K> bo) {
		this.bo = bo;
	}
	*/
	/** Retorna uma Entidade vazia*/
	
	
	/** Retorna um prametro enviado via request*/
	protected String getRequestParam(String name){
			String ret = "";
			try{
				Map<String, String> params  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				if(params.containsKey(paramName)){
					ret = params.get(paramName); 
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
			return ret;
	}

	public String getParamName() {
		return paramName;
	}

	
	protected void sendMsg(String title,String msg){
		RequestContext.getCurrentInstance().showMessageInDialog(
				new FacesMessage(FacesMessage.SEVERITY_INFO, title ,msg));
	}

	
	public Usuario getCurrentUser(){
		
		return null;
	}
	
}
