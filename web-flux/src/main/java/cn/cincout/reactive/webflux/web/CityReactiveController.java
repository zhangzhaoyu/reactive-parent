package cn.cincout.reactive.webflux.web;

import cn.cincout.reactive.webflux.domain.City;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 19-6-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
@RequestMapping("/reactive")
public class CityReactiveController {
    @Resource
    private ReactiveRedisTemplate<String, City> reactiveRedisTemplate;

    @GetMapping(value = "/x")
    public Mono<String> test() {
        return Mono.just("hello");
    }

    @GetMapping(value = "/{id}")
    public Mono<City> findById(@PathVariable("id") int id) {
        String key = "CITY_" + id;
        Mono<City> city = reactiveRedisTemplate.opsForValue().get(key);
        return city;
    }

    @PostMapping
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "CITY_" + city.getId();
        return reactiveRedisTemplate.opsForValue().getAndSet(key, city);
    }
}
