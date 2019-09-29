package cn.cincout.reactor.reactornetty.tcp;

import io.netty.channel.ChannelOption;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;

/**
 * Created by zhaoyu on 19-7-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class TcpServer {
    public static void main(String[] args) {
        DisposableServer server =
                reactor.netty.tcp.TcpServer
                        .create()
                        .host("localhost")
                        .port(8080)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .wiretap(true)
                        .handle((inbound, outbound) -> outbound.sendString(Mono.just("hello world")))
                        .bindNow();

        server.onDispose().block();
    }
}
