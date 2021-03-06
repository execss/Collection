package top.byteinfo.test2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MinimalServlets {
    public static Server createServer(int port)
    {
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(port);

        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.

        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        handler.addServletWithMapping(HelloServlet.class, "/*");

        return server;
    }

    public static void main(String[] args) throws Exception
    {
        // Create a basic jetty server object that will listen on port 8080.
//        int port = ExampleUtil.getPort(args, "jetty.http.port", 8080);
        int port = 8080;
        Server server = createServer(port);

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server thread is done executing.
        server.join();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws IOException
        {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}
