package cn.cincout.reactive.webflux.service;

import cn.cincout.reactive.webflux.domain.Customer;
import cn.cincout.reactive.webflux.domain.CustomerExample;
import cn.cincout.reactive.webflux.inf.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

/**
 * Created by zhaoyu on 19-9-26.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class ReactiveCustomerServiceImpl implements CustomerService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Mono<Integer> save(Customer customer) {
        Mono<Integer> mono = Mono.fromCallable(() -> customerMapper.insert(customer))
                .publishOn(scheduler);
        return mono;
    }

    @Override
    public Mono<Customer> find(int id) {
        Mono<Customer> customer =
                Mono.defer(() -> Mono.just(this.customerMapper.selectByPrimaryKey(id)))
                .subscribeOn(scheduler);
        return customer;
    }

    @Override
    public Flux<Customer> findAll() {
        Flux<Customer> defer = Flux.defer(() -> Flux.fromIterable(this.customerMapper.selectByExample(new CustomerExample())));
        return defer.subscribeOn(scheduler);
    }
}
