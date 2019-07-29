package cn.cincout.reactive.webflux.web;

import cn.cincout.reactive.webflux.domain.City;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 19-6-24.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
@RequestMapping("/block")
public class CityController {
    @Resource
    private RedisTemplate<String, City> redisTemplate;

    @GetMapping(value = "/{id}")
    public City findById(@PathVariable("id") int id) {
        String key = "CITY_" + id;
        City city = redisTemplate.opsForValue().get(key);
        return city;
    }
}
