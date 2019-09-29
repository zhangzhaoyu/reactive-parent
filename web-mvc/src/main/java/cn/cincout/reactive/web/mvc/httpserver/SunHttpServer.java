package cn.cincout.reactive.web.mvc.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created by zhaoyu on 19-9-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class SunHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9999), 0);
        httpServer.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String content = "<a>hello world</a>";
                httpExchange.getResponseHeaders().set("Content-Type", "application/xml");
                //httpExchange.sendResponseHeaders(200, content.length());
                OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(content.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            }
        });
        httpServer.start();
        System.out.println("started");
    }
}
