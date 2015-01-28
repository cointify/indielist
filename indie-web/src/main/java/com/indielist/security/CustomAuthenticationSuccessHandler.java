package com.indielist.security;

import com.indielist.domain.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author jsingh on 15-01-11.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LogManager.getLogger();
    private static final String ADMIN_SUCCESS_VIEW = "admin/home";
    private static final String USER_SUCCESS_VIEW = "user/home";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("Authentication was successful for user " + authentication.getName() + ". Now, redirecting to home page.");
        String successView = null;

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(Role.ADMINISTRATOR)) {
                successView = ADMIN_SUCCESS_VIEW;
                break;
            } else if (grantedAuthority.getAuthority().equals(Role.USER)) {
                successView = USER_SUCCESS_VIEW;
                break;
            }
        }

        if(successView == null) {
            throw new IllegalStateException("Unable to determine success view from user [username=" + authentication.getName() + "] roles. System cannot redirect to success view as it's null.");
        }
        response.sendRedirect(successView);
    }
}

