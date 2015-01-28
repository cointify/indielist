package com.indielist.service;

import com.indielist.domain.BaseEntity;
import com.indielist.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author jsingh on 15-01-11.
 */
@Service
@Transactional
@Scope("prototype")
public class UserService extends BaseService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
        BaseEntity.setEntityManager(entityManager);
    }

    public User getUserByUsername(String username) {
        return User.findUserByUsername(username);
    }
    
    public void createNewUser(User user) {
        // Encode password before persisting
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.persist();
    }
    
    public void updateUser(User user) {
        user.merge();
    }
}
