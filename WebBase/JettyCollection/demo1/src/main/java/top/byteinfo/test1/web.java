package top.byteinfo.test1;

import org.eclipse.jetty.server.Server;
import top.byteinfo.test1.api.HelloHandler;

public class web {
    public static Server createServer ( int port)
    {
        Server server = new Server(port);
        server.setHandler(new HelloHandler());
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(801);
        server.start();
        server.join();



    }
}
