package com.web;

import com.web.configs.Configurations;
import com.web.servlet.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Mains {

    public static void main(String[] args) throws Exception {
          final int DEFAULT_PORT = 8080;
          final String CONTEXT_PATH = "/";
          final String MAPPING_URL = "/*";

//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(Configurations.class);
//
//        ServletContextHandler handler = new ServletContextHandler();
//        handler.setContextPath(CONTEXT_PATH);
//        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
//        handler.addEventListener(new ContextLoaderListener(context));


        Server server = new Server( DEFAULT_PORT);
        server.setHandler(new HelloServlet());
        server.start();
        server.join();
    }
}
