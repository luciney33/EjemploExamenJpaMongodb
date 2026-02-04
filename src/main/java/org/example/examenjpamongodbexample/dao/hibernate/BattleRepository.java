package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaBattleEntity;

public interface BattleRepository {
    int save(JpaBattleEntity battle);

}
