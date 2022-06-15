package top.byteinfo.test3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import top.byteinfo.test2.MinimalServlets;
import top.byteinfo.util.ExampleUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.ASYNC;
import static javax.servlet.DispatcherType.REQUEST;

public class OneServletContext {
    public static Server createServer(int port, Resource baseResource)
    {
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setBaseResource(baseResource);
        server.setHandler(context);

        // add hello servlet
        context.addServlet(MinimalServlets.HelloServlet.class, "/hello/*");

        // Add dump servlet on multiple url-patterns
        ServletHolder debugHolder = new ServletHolder("debug", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doGet(req, resp);
            }
        });
        context.addServlet(debugHolder, "/dump/*");
        context.addServlet(debugHolder, "*.dump");

        // add default servlet (for error handling and static resources)
        context.addServlet(DefaultServlet.class, "/");

        // sprinkle in a few filters to demonstrate behaviors
        context.addFilter(TestFilter.class, "/test/*", EnumSet.of(REQUEST));
        context.addFilter(TestFilter.class, "*.test", EnumSet.of(REQUEST, ASYNC));

        // and a few listeners to show other ways of working with servlets
        context.getServletHandler().addListener(new ListenerHolder(InitListener.class));
        context.getServletHandler().addListener(new ListenerHolder(RequestListener.class));

        return server;
    }

    public static void main(String[] args) throws Exception
    {
        int port = ExampleUtil.getPort(args, "jetty.http.port", 8080);
        Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));

        Server server = createServer(port, new PathResource(tempDir));

        server.start();
        server.dumpStdErr();
        server.join();
    }

    public static class TestFilter implements Filter
    {
        @Override
        public void init(FilterConfig filterConfig)
        {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
            if (response instanceof HttpServletResponse)
            {
                HttpServletResponse httpServletResponse = (HttpServletResponse)response;
                httpServletResponse.setHeader("X-TestFilter", "true");
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy()
        {

        }
    }

    public static class InitListener implements ServletContextListener
    {
        @Override
        public void contextInitialized(ServletContextEvent sce)
        {
            sce.getServletContext().setAttribute("X-Init", "true");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce)
        {
        }
    }

    public static class RequestListener implements ServletRequestListener
    {
        @Override
        public void requestInitialized(ServletRequestEvent sre)
        {
            sre.getServletRequest().setAttribute("X-ReqListener", "true");
        }

        @Override
        public void requestDestroyed(ServletRequestEvent sre)
        {
        }
    }
}
