package org.example.examenjpamongodbexample.dao.hibernate.jpaRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;
import org.example.examenjpamongodbexample.dao.utilities.JPAUtil;

public class JpaWeaponRepository implements WeaponRepository {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public JpaWeaponRepository(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }
    @Override
    public JpaWeaponEntity findByName(String name) {
        em = jpaUtil.getEntityManager();
        JpaWeaponEntity weapon = null;
        try {
            weapon = em.createQuery("SELECT w FROM JpaWeaponEntity w WHERE w.wName = :name", JpaWeaponEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw e;
        } finally {
            if (em != null) em.close();
        }
        return weapon;
    }

    @Override
    public int save(JpaWeaponEntity weapon) {
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(weapon);
            em.flush();
            tx.commit();
            return weapon.getId();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(JpaWeaponEntity weapon) {

    }

    @Override
    public void update(JpaWeaponEntity weapon) {

    }
}
