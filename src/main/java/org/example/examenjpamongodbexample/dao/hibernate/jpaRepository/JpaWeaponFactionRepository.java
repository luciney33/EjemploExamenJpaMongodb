package org.example.examenjpamongodbexample.dao.hibernate.jpaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponFactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponFactionEntity;
import org.example.examenjpamongodbexample.dao.utilities.JPAUtil;

import java.util.List;

public class JpaWeaponFactionRepository implements WeaponFactionRepository {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public JpaWeaponFactionRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }
    @Override
    public List<JpaWeaponFactionEntity> getAll() {
        return List.of();
    }

    @Override
    public JpaWeaponFactionEntity getWeaponOfFaction(JpaWeaponFactionEntity entity) {
        return null;
    }

    @Override
    public int save(JpaWeaponFactionEntity relation) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(relation);
            em.flush();
            tx.commit();
            return relation.getId();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
