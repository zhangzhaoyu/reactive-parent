package cn.cincout.reactor.reactor.flux;

import lombok.NonNull;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaoyu on 19-6-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class FluxSample {

    public void generateFlux() {
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    public void subscribe() {
        Disposable disposable = Flux.just("alibaba", "tenxun", "mneituan")
                .filter(s -> s.length() > 3)
                .map(s -> s.contains("@qq.com"))
                .subscribe(System.out::println);
        System.out.println("hello");

    }

    public void subscribeException() {
        Flux.range(1, 4).map(i -> {
            if (i < 3) {return i;}
            throw new IllegalArgumentException("illegal arg");
        }).subscribe(System.out::println, e -> {
            System.out.println(e.getMessage());
        });
    }

    public void subscribeExceptionOnComplete() {
        Flux.range(1, 4).map(i -> {
            if (i < 10) {return i;}
            throw new IllegalArgumentException("illegal arg");
        }).subscribe(System.out::println, e -> System.out.println(e.getMessage()), () -> {
            System.out.println("complete");
        });
    }

    public void reduce() {
        List<String> data = Arrays.asList("hello", "world", "tramp");
        String init = "good ";

        Mono<String> result = Flux.fromIterable(data).reduce(init, (tmp, itemList) -> {
            tmp += itemList + " ";
            System.out.println(init + " : " + tmp + " : " + itemList);
            return tmp;
        });

        result.subscribe(System.out::println);
    }

    public static void nullTest(@NonNull String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        FluxSample fluxSample = new FluxSample();

        // fluxSample.subscribeException();
        //
        // fluxSample.subscribeExceptionOnComplete();

        fluxSample.reduce();
        fluxSample.generateFlux();
    }
}
