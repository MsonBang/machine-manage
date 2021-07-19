package com.golaxy.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.golaxy.machine.**","com.golaxy.machine.mapper"})
public class MiaoshaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaoApplication.class, args);
    }

}
