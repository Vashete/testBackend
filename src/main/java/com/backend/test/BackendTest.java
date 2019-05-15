package com.backend.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Main class to configure the Jetty Server
 * @author Vashete
 * @version 20190515
 */
public class BackendTest {

	
	public static void main(String[] args) {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		Server jettyServer = null;
		try {
			Server server = new Server(8080);
	                
			context.setContextPath("/");
	        server.setHandler(context);

	        ServletHolder serHol = context.addServlet(ServletContainer.class, "/rest/*");
	        serHol.setInitOrder(1);
	        serHol.setInitParameter("jersey.config.server.provider.packages", 
	                "com.backend.test.controllers");
	        server.start();
            server.join();
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append("Failed to start the server. Error: ").append(e.getClass().getSimpleName()).append(" Message: ")
					.append(e.getMessage());
			System.out.println(sb.toString());
		} 
		finally {
			if (jettyServer != null)
				jettyServer.destroy();
		}

	}

}
