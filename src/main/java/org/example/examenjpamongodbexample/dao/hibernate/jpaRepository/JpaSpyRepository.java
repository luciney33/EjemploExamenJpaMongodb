package org.example.examenjpamongodbexample.dao.hibernate.jpaRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.example.examenjpamongodbexample.dao.hibernate.SpyRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaSpyEntity;
import org.example.examenjpamongodbexample.dao.utilities.JPAUtil;

public class JpaSpyRepository implements SpyRepository {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public JpaSpyRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public JpaSpyEntity findByName(String name) {
        em = jpaUtil.getEntityManager();
        JpaSpyEntity spy = null;
        try {
            spy = em.createNamedQuery("GET_SPY", JpaSpyEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) em.close();
        }
        return spy;
    }

    @Override
    public int save(JpaSpyEntity spy) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(spy);
            em.flush();
            tx.commit();
            return spy.getId();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(JpaSpyEntity spy) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(em.merge(spy));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }
}
