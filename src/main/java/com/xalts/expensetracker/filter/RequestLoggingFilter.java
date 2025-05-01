package com.xalts.expensetracker.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for logging incoming HTTP requests.
 * Logs request method, URI, and user information.
 */
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    /**
     * Process each incoming request and log its details.
     * @param request The HTTP request
     * @param response The HTTP response
     * @param filterChain The filter chain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userId = "anonymous";
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            userId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }

        logger.info("Request: {} {} from user: {}", request.getMethod(), request.getRequestURI(), userId);
        filterChain.doFilter(request, response);
    }
} 