package cn.cincout.reactive.web.mvc.httpserver;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

/**
 * Created by zhaoyu on 19-9-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class RequestHandler {
    public static NettyOutbound handle(HttpServerRequest request, HttpServerResponse response) {
        HttpHeaders httpHeaders = request.requestHeaders();
        return response.status(HttpResponseStatus.OK)
                .header(HttpHeaderNames.CONTENT_LENGTH, "12")
                .sendString(Mono.just("Hello World!"));
    }
}
