package cn.cincout.reactor.reactornetty.tcp;

import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;

/**
 * Created by zhaoyu on 19-7-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class TcpClient {
    public static void main(String[] args) {
        Connection connection = reactor.netty.tcp.TcpClient
                .create()
                .host("localhost")
                .port(8080)
                .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello server")))
                .connectNow();

        connection.onDispose().block();
    }
}
