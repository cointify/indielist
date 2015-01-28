package com.indielist.domain;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @author jsingh on 15-01-10.
 */
abstract public class BaseEntity<T> {

    protected Class<T> type;

    private static ThreadLocal<EntityManager> em = new ThreadLocal<>();

    public BaseEntity() {}

    public BaseEntity(Class<T> type) {
        this.type = type;
    }

    public static EntityManager getEntityManager() {
        return em.get();
    }

    public static EntityManager em(){
        return getEntityManager();
    }

    public static void setEntityManager(EntityManager entityManager){
        em.set(entityManager);
    }

    @SuppressWarnings("unchecked")
    public T persist() {
        em().persist(this);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T merge() {
        em().merge(this);
        return (T) this;
    }

    public void delete() {
        em().remove(this);
    }

    public static Object getSingleResultOrNull(Query query){
        try {
            return query.getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    public static void markQueryReadOnly(Query q){
        q.setHint("org.hibernate.readOnly", Boolean.TRUE);
    }
}
