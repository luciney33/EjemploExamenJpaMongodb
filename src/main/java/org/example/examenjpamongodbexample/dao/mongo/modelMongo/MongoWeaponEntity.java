package org.example.examenjpamongodbexample.dao.mongo.modelMongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoWeaponEntity {
    private String name;
    private double price;
}
