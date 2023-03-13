package shop.mtcoding.aopstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.aopstudy.handler.aop.Hello;

@RestController
public class HelloController {

    @Hello
    @GetMapping("/v1")
    public String v1() {
        return "v1";
    }

    @Hello
    @GetMapping("/v2")
    public String v2(String username) {
        return "v2";
    }

    @Hello
    @GetMapping("/v3")
    public String v3(String username) {
        return "v3";
    }
}
