package com.softserve.osbb.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Created by nazar.dovhyy on 08.07.2016.
 */

@PropertySource("classpath:/config.properties")
@Configuration
@SpringBootApplication
@Import({ServiceApplication.class/*,SecurityConfiguration.class*/})
@ComponentScan(basePackages = {"com.softserve.osbb"})
@EnableScheduling
public class WebAppConfiguration extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebAppConfiguration.class
        }, args);
    }

    private static final String[] STATIC_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String MAPPING_PATTERN = "/**";
        if (!registry.hasMappingForPattern(MAPPING_PATTERN))
            registry.addResourceHandler(MAPPING_PATTERN)
                    .addResourceLocations(STATIC_RESOURCE_LOCATIONS);
        super.addResourceHandlers(registry);
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new ClassPathResource("config.properties")/*,                                     //default properties
                new FileSystemResource("/home/nataliia/myosbb1/deployment/external.properties")*/);        //external properties
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }



}
