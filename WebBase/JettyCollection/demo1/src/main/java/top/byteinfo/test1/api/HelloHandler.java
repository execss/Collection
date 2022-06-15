package top.byteinfo.test1.api;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet是提供处理 HTTP 请求的应用程序逻辑的标准方式
 *
 *
 * 传递给handle方法的参数是：
 *
 * target– 请求的目标，它可以是 URI 或来自指定调度程序的名称。
 *
 * baseRequest– Jetty 可变请求对象，始终未包装。
 *
 * request– 不可变的请求对象，它可能已被过滤器或 servlet 包装。
 *
 * response– 响应，可能已被过滤器或 servlet 包装。
 */
public class HelloHandler extends AbstractHandler {
    final String greeting;
    final String body;

    public HelloHandler()
    {
        this("Hello Worlds");
    }

    public HelloHandler(String greeting)
    {
        this(greeting, null);
    }

    public HelloHandler(String greeting, String body)
    {
        this.greeting = greeting;
        this.body = body;
    }
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1>");
        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }
}
