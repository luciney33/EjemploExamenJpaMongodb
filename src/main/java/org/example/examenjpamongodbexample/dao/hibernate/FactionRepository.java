package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;

import java.util.List;

public interface FactionRepository {
    List<JpaFactionEntity> getAll();
    JpaFactionEntity fingByName(String name);
    String save (JpaFactionEntity entity);
}
