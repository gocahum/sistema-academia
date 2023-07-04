package com.gocahum.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApiApplication.class, args);
    }

}
