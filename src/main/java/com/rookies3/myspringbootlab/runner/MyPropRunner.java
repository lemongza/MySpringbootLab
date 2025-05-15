package com.rookies3.myspringbootlab.runner;

import com.rookies3.myspringbootlab.property.MyPropProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyPropProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("${myprop.username}: " + username);
        System.out.println("${myprop.port}: " + port);

        // MyPropProperties 객체를 주입(Injection) 받아서 getter 메서드를 출력하기
        System.out.println("MyPropProperties.getUsername: " + properties.getUsername());
        System.out.println("MyPropProperties.getPort: " + properties.getPort());
    }
}
