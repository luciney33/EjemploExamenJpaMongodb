package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;

public interface BattleRepository {
    int save(JpaWeaponEntity weapon);

}
