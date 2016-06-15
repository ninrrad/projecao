package br.projecao.procura.beans.generic;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="generic_converter")
public class GenericConverter implements Converter, Serializable {
	private static final long serialVersionUID = 5646548978978976643L;
	
	public static final  String CONVERTER_KEY_MAP 		= "GENERIC_CONVERTER_MAP";
	public static final  String CONVERTER_KEY_TIME_MAP 	= "GENERIC_CONVERTER_TIME_MAP";
	public static final  int    CONVERTER_OBJECT_TIME_LIFE 	= 1000 * 60 * 5; // 5 Min de vida apra um objeto no mapa
	
	protected class WrraperClass{
		Object  val;
		long   time;
		protected WrraperClass(Object val, long time) {
			this.val = val;
			this.time = time;
		}
	}
	
	protected <T> Map<String,T> getConverterMap(FacesContext context,String key){
		Map<String,Object> viewMap = context.getViewRoot().getViewMap();
		Map<String,T> converterMap =null;
		
		if (viewMap.containsValue(CONVERTER_KEY_MAP)){
			converterMap =	(Map<String,T>) viewMap.get(CONVERTER_KEY_MAP);
		}else{
			converterMap = new HashMap<String,T>();
			viewMap.put(CONVERTER_KEY_MAP,converterMap);
		}
		return converterMap;
	}
	
	protected void checkTimeOut(Map<String,WrraperClass> converterMap){
		long cTime = System.currentTimeMillis();
		for (Map.Entry<String, WrraperClass> e : converterMap.entrySet()){
			if(cTime - e.getValue().time > CONVERTER_OBJECT_TIME_LIFE){
				converterMap.remove(e.getKey());
			}
		}
	}
	
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		Object ret = null;
		try{
			Map<String,WrraperClass> converterMap   = getConverterMap(context,CONVERTER_KEY_MAP);
			WrraperClass wC=converterMap.remove(value);
			ret =  (wC!=null ? wC.val : null);
			checkTimeOut(converterMap);
		} catch (Exception e) {
			System.out.println("GenericConverter Error:"+e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public String getAsString(FacesContext context, UIComponent component,Object value) {
		String ret = null;
		try{
			Method getId = value.getClass().getMethod("getId", null);
			ret =value.getClass().getName()+getId.invoke(value, null).toString();
			Map<String,WrraperClass> converterMap     = getConverterMap(context,CONVERTER_KEY_MAP);
			converterMap.put(ret,new WrraperClass(value,System.currentTimeMillis()));
			checkTimeOut(converterMap);
		} catch (Exception e) {
			System.out.println("GenericConverter Error:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}
}