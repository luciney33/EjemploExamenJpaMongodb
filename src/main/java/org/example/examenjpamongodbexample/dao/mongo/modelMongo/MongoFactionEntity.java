package org.example.examenjpamongodbexample.dao.mongo.modelMongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoFactionEntity {
    private String name;
    private String contact;
    private String planet;
    private int numCS;
    private LocalDate dateLastPurchase;

    @Singular
    private List<MongoWeaponEntity> weapons;
}
