package com.softserve.osbb.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by cavayman on 19.07.2016.
 */
public class SecurityAppConfig
    extends AbstractSecurityWebApplicationInitializer {

        public SecurityAppConfig() {
            super(SecurityConfig.class);
        }
}
