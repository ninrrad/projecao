package br.projecao.procura.seguranca;

import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import javax.security.auth.login.Configuration;
import javax.servlet.http.HttpServlet;

public class ServletConf extends HttpServlet {

	public static final String APLICATION_NAME = "busca";
	private static final long serialVersionUID = 5835062135037950209L;

	public static class LoginConfiguration extends Configuration {
		@Override
		public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
			if (name.equals(APLICATION_NAME)) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("debug", Boolean.TRUE);
				return new AppConfigurationEntry[] { new AppConfigurationEntry(LoginModuleJaas.class.getName(),	LoginModuleControlFlag.REQUIRED, map) };
			}else return null;
		}
	}

	static {
		Configuration.setConfiguration(new LoginConfiguration());
	}

}
