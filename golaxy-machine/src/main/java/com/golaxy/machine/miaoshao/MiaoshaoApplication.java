package com.golaxy.machine.miaoshao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.golaxy.machine.miaoshao.mapper"})
public class MiaoshaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaoApplication.class, args);
    }

}
