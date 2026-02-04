package org.example.examenjpamongodbexample.dao.hibernate.jpaRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.examenjpamongodbexample.dao.hibernate.BattleRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaBattleEntity;
import org.example.examenjpamongodbexample.dao.utilities.JPAUtil;

public class JpaBattleRepository implements BattleRepository {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public JpaBattleRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }


    @Override
    public int save(JpaBattleEntity battle) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(battle);
            em.flush();
            tx.commit();
            return battle.getId();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
