package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;

import java.util.List;

public interface WeaponRepository {
    List<JpaWeaponEntity> getAll();
    JpaWeaponEntity findByName(String name);
    int save(JpaWeaponEntity weapon);
    void delete(JpaWeaponEntity weapon);
    void update(JpaWeaponEntity weapon);

}
