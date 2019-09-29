package cn.cincout.reactive.web.mvc.httpserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zhaoyu on 19-9-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
@Slf4j
public class HttpTestServer {
    DisposableServer server;

    @PostConstruct
    public void init() {
        server = HttpServer
                .create().host("localhost")
                .port(8080)
                .route(routes ->
                        routes.get("/", RequestHandler::handle))
                .bindNow();
        log.info("netty http server started");
    }

    @PreDestroy
    public void destroy() {
        server.onDispose().block();
        log.info("netty http server stopped");
    }
}
