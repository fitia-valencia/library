package com.example.BiblioFarany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class WebConfig {

    // This class can be used to configure web-related settings, such as view resolvers, interceptors, etc.
    // Currently, it is empty, but you can add configurations as needed.

    // Example: You might want to add a view resolver or configure static resource handling here.
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
}
