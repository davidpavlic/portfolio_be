package com.david.dev.portfolio_be.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    @Value("${crud.master-password}")
    private String masterPassword;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationFilter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // --- IMPORTANT: Define which requests need authentication ---
        // For example, allow GET requests to pass through without authentication.
        // Only apply authentication for POST, PUT, DELETE operations, etc.
        if ("GET".equalsIgnoreCase(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // The client should send the password in a custom header, e.g., "X-Auth-Password"
        String providedPassword = httpRequest.getHeader("X-Auth-Password");

        if (providedPassword == null || !passwordEncoder.matches(providedPassword, masterPassword)) {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: Invalid or missing password.");
            return;
        }

        chain.doFilter(request, response);
    }
}