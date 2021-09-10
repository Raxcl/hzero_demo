package org.hand.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
/**
 * @author 33265
 */
public class Hzeroorder33265Application {

    public static void main(String[] args) {
        SpringApplication.run(Hzeroorder33265Application.class, args);
    }
}


