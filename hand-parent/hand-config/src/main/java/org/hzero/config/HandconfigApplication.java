package org.hzero.config;

import org.hzero.autoconfigure.config.EnableHZeroConfig;
import org.springframework.cloud.config.server.config.ConfigServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroConfig
@EnableDiscoveryClient
@SpringBootApplication(exclude = {ConfigServerAutoConfiguration.class})

public class HandconfigApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandconfigApplication.class, args);
        }

}