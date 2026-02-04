package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponFactionEntity;

import java.util.List;

public interface WeaponFactionRepository {
    List<JpaWeaponFactionEntity> getAll();
    JpaWeaponFactionEntity getWeaponOfFaction(JpaWeaponFactionEntity entity);
    int save (JpaWeaponFactionEntity relation);
}
