package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;

public interface WeaponRepository {
    JpaWeaponEntity findByName(String name);
    int save(JpaWeaponEntity weapon);
    void delete(JpaWeaponEntity weapon);
    void update(JpaWeaponEntity weapon);

}
