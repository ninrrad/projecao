package br.projecao.procura.beans.generic;

import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class ViewContext implements Context {

    public Class getScope() {
        return ViewScoped.class;
    }

    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        Bean<T> bean = (Bean<T>) contextual;
        Map viewMap = getViewMap();
        if(viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            T t = bean.create(creationalContext);
            viewMap.put(bean.getName(), t);
            return t;
        }
    }

    private Map getViewMap() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = fctx.getViewRoot();
        return viewRoot.getViewMap(true);
    }

    public <T> T get(Contextual<T> contextual) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if(viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            return null;
        }
    }

    public boolean isActive() {
        return true;
    }
}