package org.example.examenjpamongodbexample.dao.hibernate;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaSpyEntity;

public interface SpyRepository {
    void delete(JpaSpyEntity spy);
}
