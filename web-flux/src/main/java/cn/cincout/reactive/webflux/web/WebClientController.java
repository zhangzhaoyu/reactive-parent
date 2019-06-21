package cn.cincout.reactive.webflux.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by zhaoyu on 19-6-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
@Slf4j
public class WebClientController {
    @GetMapping("/webclient")
    public Mono<String> webclient() {
        log.info("request for web client");
        WebClient webClient = WebClient.create("http://localhost:8080/");
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .flatMap(clientResponse -> Mono.just("webclient : " + clientResponse.toString()));
    }
}
