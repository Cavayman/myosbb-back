package com.softserve.osbb.config;

import com.softserve.osbb.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by nazar.dovhyy on 08.07.2016.
 */
@SpringBootApplication
@Import({PersistenceConfiguration.class,SecurityAppConfig.class})
@ComponentScan(basePackages = {"com.softserve.osbb"})
public class WebAppConfiguration extends WebMvcConfigurerAdapter {


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

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{WebAppConfiguration.class
                }, args);
    }

    //for test only will be DELETED in next versions
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/home").setViewName("index.html");
        registry.addViewController("/error").setViewName("login.html");


    }
}
