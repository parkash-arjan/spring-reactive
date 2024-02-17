package com.example.springreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebFluxTest {

  @Test
  public void testMono() {
    Mono<Object> mono =
        Mono.just("Single Value").then(Mono.error(new RuntimeException("Exception"))).log();

    mono.subscribe(System.out::println, (exception) -> System.out.println(exception.getMessage()));
  }

  @Test
  public void testFlux() {
    Flux<String> flux =
        Flux.just("Item#1", "Item#2", "Item#3")
            .concatWithValues("Item#4", "Item#5")
            .concatWith(Flux.error(new RuntimeException("Error")))
            .concatWithValues("Item#6", "Item#7")
            .log();
    flux.subscribe(System.out::println, (exception) -> System.out.println(exception.getMessage()));
  }
}
