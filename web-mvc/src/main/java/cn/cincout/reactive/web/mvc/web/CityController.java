package cn.cincout.reactive.web.mvc.web;

import cn.cincout.reactive.web.mvc.domain.City;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 19-6-24.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
public class CityController {
    @Resource
    private RedisTemplate<String, City> redisTemplate;

    @GetMapping(value = "/{id}")
    public City findById(@PathVariable("id") int id) {
        String key = "MVC_" + id;
        City city = redisTemplate.opsForValue().get(key);
        return city;
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        String key = "MVC_" + city.getId();
        return redisTemplate.opsForValue().getAndSet(key, city);
    }
}
