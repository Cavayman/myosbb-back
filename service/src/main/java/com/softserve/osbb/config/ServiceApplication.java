package com.softserve.osbb.config;

import com.softserve.osbb.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PersistenceConfiguration.class)
@ComponentScan(basePackages = "com.softserve.osbb.service")
public class ServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceApplication.class, args);

    }

}