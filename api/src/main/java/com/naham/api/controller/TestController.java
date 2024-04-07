package com.naham.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/hello")
    public Collection<String> sayHello() {
        log.info("Hello endpoint!");
        return IntStream.range(0, 10)
                .mapToObj(i -> "Hello number " + i)
                .collect(Collectors.toList());
    }


    @GetMapping("/")
    public String greetings() {
        log.info("Greetings enpdoint on /");
        return "Hello word!";
    }
}
