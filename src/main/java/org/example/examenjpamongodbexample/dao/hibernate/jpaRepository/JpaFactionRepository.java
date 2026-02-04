package org.example.examenjpamongodbexample.dao.hibernate.jpaRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.example.examenjpamongodbexample.dao.hibernate.FactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.dao.utilities.JPAUtil;

import java.util.ArrayList;
import java.util.List;

public class JpaFactionRepository implements FactionRepository {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public JpaFactionRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public List<JpaFactionEntity> getAll() {
        List<JpaFactionEntity> factionList = new ArrayList<>();
        em = jpaUtil.getEntityManager();
        try {
            factionList = em.createNamedQuery("GET_ALL_FACTIONS", JpaFactionEntity.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null)  em.close();
        }
        return factionList;
    }

    @Override
    public JpaFactionEntity fingByName(String name) {
        em = jpaUtil.getEntityManager();
        JpaFactionEntity faction = null;
        try {
            faction = em.find(JpaFactionEntity.class, name);
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) em.close();
        }
        return faction;
    }

    @Override
    public String save(JpaFactionEntity entity) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(entity);
            em.flush();
            tx.commit();
            return entity.getFName();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
