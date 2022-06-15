package com.example.tomcat;

import com.example.tomcat.rest.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {



    public static void main(String[] args) throws LifecycleException {


        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.

        tomcat.setBaseDir("temp");
        tomcat.setPort(80);

        String docBase = new File(".").getAbsolutePath();
        String contextPath = "";
        Context context = tomcat.addContext(contextPath, docBase);


        String servletName = "Servlet1";
        String urlPattern = "/hello";

        HelloServlet helloServlet =new HelloServlet();

        tomcat.addServlet(contextPath, servletName, helloServlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
