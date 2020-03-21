package com.qian.ribbon_consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;//注入restTemalate
    public String sayHello(){

        //通过rest调用 调用provider服务
        return restTemplate.getForObject("http://ribbon_provider/hello?name=zhangtaifeng",String.class); // 提供一个hello World
    }
}
