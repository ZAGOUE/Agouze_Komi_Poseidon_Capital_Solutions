package com.nnk.springboot.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * CustomAuthenticationSuccessHandler redirects users after successful authentication
 * based on their roles.

 * ROLE_ADMIN is redirected to /user/list
 * Other users are redirected to /bidList/list
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * Handles post-authentication redirection based on the user's role.
     *
     * @param request        the HttpServletRequest
     * @param response       the HttpServletResponse
     * @param authentication the Authentication object containing user details
     * @throws IOException      if redirection fails
     * @throws ServletException if an error occurs during request handling
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect("/user/list");
        } else {
            response.sendRedirect("/bidList/list");
        }
    }
}
