package com.softserve.osbb.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
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
        // to run with external properties file type: "mvn clean install -Dspring.config.location="
        // and full path to property file in deployment package
        // for example:
        // mvn clean install -Dspring.config.location=/home/nataliia/myosbb1/deployment/external.properties
        // you can add this to "Edit Configuration" in Intellij IDEA to field "VM Options"
        // but only "-Dspring.config.location="
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter((Filter) new JwtFilter());
        registrationBean.addUrlPatterns("/restful/*");

        return registrationBean;
    }

}
