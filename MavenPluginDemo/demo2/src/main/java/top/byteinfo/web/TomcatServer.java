package top.byteinfo.web;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import top.byteinfo.web.servlet.HelloServlet;

import java.io.File;

public class TomcatServer {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.

        tomcat.setBaseDir("temp");
        tomcat.setPort(801);

        String docBase = new File(".").getAbsolutePath();
        String contextPath = "";
        Context context = tomcat.addContext(contextPath, docBase);


        String servletName = "Servlet1";
        String urlPattern = "/hello";

        HelloServlet helloServlet =new HelloServlet();

        tomcat.addServlet(contextPath, servletName, helloServlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
        tomcat.getServer().await();
    }
}
