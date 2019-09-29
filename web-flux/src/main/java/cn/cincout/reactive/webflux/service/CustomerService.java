package cn.cincout.reactive.webflux.service;

import cn.cincout.reactive.webflux.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by zhaoyu on 19-9-26.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface CustomerService {
    Mono<Integer> save(Customer customer);
    Mono<Customer> find(int id);
    Flux<Customer> findAll();
}
