package com.rookies3.myspringbootlab.runner;

import com.rookies3.myspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Logger 구현체 => " + logger.getClass().getName());
        logger.info("${myprop.username}: {}", username);
        logger.info("${myprop.port}: {}", port);

        // MyPropProperties 객체를 주입(Injection) 받아서 getter 메서드를 출력하기
        logger.debug("MyPropProperties.getUsername: {}", properties.getUsername());
        logger.debug("MyPropProperties.getPort: {}", properties.getPort());
    }
}
