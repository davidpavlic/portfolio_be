package com.david.dev.portfolio_be.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final AuthenticationFilter authenticationFilter;

    public FilterConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistration() {
        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(authenticationFilter);
        // Define which URL patterns this filter should apply to.
        // For your LLMChatEntryController, it's under "/llmchatentry".
        registration.addUrlPatterns("/llmchatentry/*");
        registration.addUrlPatterns("/projectcard/*");
        registration.addUrlPatterns("/llmchatuser/*");
        registration.setOrder(1);
        return registration;
    }
}