package com.iac.ambit.filter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.utils.Config;


   
public class AmbitListener implements ServletContextListener {

	private ServletContext context;
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		context = servletContextEvent.getServletContext();
		
		
		DBConfig.init(context.getRealPath(context
				.getInitParameter("DBConfig-file")));
        
		Config.init(context.getRealPath(context.getInitParameter("Config-file")));
		
		

	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Config.destroy();
		//DBConfig.destroy();

	}

}
