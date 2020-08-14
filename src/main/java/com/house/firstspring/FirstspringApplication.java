package com.house.firstspring;

import com.house.firstspring.domain.User;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstspringApplication.class, args);

        Logger logger = LoggerFactory.getLogger(FirstspringApplication.class);

        final User damian = new User(123,"damian");
        logger.info(damian.getName());
    }

}
