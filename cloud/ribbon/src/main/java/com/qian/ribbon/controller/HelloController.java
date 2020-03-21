package com.qian.ribbon.controller;

import com.qian.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public String SayHello(String name){
        return helloService.sayHello() +"" + name;
    }
}
