package com.rookies3.myspringbootlab.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("${myprop.username}: " + username);
        System.out.println("${myprop.port}: " + port);
    }
}
