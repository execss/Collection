package top.byteinfo.test3;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import top.byteinfo.test1.api.HelloHandler;

public class OneContext {
    public static Server createServer(int port)
    {
        Server server = new Server(port);

        // Add a single handler on context "/hello"
        ContextHandler context = new ContextHandler();
        context.setContextPath("/hello");
        context.setHandler(new HelloHandler());

        // Can be accessed using http://localhost:8080/hello

        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception
    {
//        int port = ExampleUtil.getPort(args, "jetty.http.port", 8080);
        int port = 8080;
        Server server = createServer(port);

        // Start the server
        server.start();
        server.join();
    }
}
