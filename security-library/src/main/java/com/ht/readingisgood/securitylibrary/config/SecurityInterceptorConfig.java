package com.ht.readingisgood.securitylibrary.config;

import com.ht.readingisgood.securitylibrary.interceptor.PreRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityInterceptorConfig implements WebMvcConfigurer {

    private PreRequestInterceptor securityInterceptor;

    @Autowired
    public SecurityInterceptorConfig(
            PreRequestInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor);
    }
}