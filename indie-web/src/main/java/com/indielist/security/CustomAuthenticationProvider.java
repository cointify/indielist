package com.indielist.security;

import com.indielist.domain.BaseEntity;
import com.indielist.domain.User;
import com.indielist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author jsingh on 15-01-11.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BaseEntity.setEntityManager(entityManager);

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.getUserByUsername(username);

        if(user == null) {
            throw new BadCredentialsException("Bad username or password.");
        }

        if(passwordEncoder.matches(password, user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Bad username or password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

