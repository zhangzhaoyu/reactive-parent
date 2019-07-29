package cn.cincout.reactor.reactornetty.tcp;

import reactor.netty.DisposableServer;

/**
 * Created by zhaoyu on 19-7-29.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class TcpClient {
    public static void main(String[] args) {
        DisposableServer server =
                reactor.netty.tcp.TcpServer
                        .create()
                        .host("localhost")
                        .port(8090)
                        .handle((inbound, outbound) -> inbound.receive().then())
                        .bindNow();

        server.onDispose().block();
    }
}
