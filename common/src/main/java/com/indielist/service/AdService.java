package com.indielist.service;

import com.indielist.domain.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author jsingh on 15-01-23.
 */
@Service
@Transactional
@Scope("prototype")
public class AdService extends BaseService {

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
        BaseEntity.setEntityManager(entityManager);
    }

    public void saveAd() {
        
    }
    
    public void updateAd() {
        
    }
    
    public void deleteAd() {

    }
    
    public void getAdById() {

    }
    
    public void getAllAds() {
                
    }
}
