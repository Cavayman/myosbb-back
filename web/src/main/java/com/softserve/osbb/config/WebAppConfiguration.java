package com.softserve.osbb.config;

import com.softserve.osbb.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by nazar.dovhyy on 08.07.2016.
 */
@SpringBootApplication
@Import(PersistenceConfiguration.class)
@ComponentScan(basePackages = {"com.softserve.osbb"})
public class WebAppConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{
                WebAppConfiguration.class,
                PersistenceConfiguration.class}, args);
    }
}
