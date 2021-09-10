package org.hzero.generator;

import org.hzero.autoconfigure.generator.EnableHZeroGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroGenerator
@EnableDiscoveryClient
@SpringBootApplication

public class HandgeneratorApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandgeneratorApplication.class, args);
        }

}