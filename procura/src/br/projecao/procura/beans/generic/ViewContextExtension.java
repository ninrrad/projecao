package br.projecao.procura.beans.generic;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

public class ViewContextExtension implements Extension{

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) {
    	System.out.println("-------------------------------------------------------------------");
    	System.out.println("Carregando configuracoes do Scopo JSF de visao para o Weld (CDI)...");
    	System.out.println("-------------------------------------------------------------------");
        event.addContext(new ViewContext());
    }
    
    

	/*public void addScope(@Observes final BeforeBeanDiscovery event)
	{
		event.addScope(ViewScoped.class, true, true);
	}

	public void registerContext(@Observes final AfterBeanDiscovery event)
	{
		event.addContext(new ViewContext());
	}*/
}

