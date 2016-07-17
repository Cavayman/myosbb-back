package com.softserve.osbb.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by cavayman on 15.07.2016.
 */
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}