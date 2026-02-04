package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaSpyEntity;

public interface SpyRepository {
    JpaSpyEntity findByName(String name);
    int save(JpaSpyEntity spy);
    void delete(JpaSpyEntity spy);
}
