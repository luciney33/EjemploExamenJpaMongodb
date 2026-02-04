package org.example.examenjpamongodbexample.dao.mongo.modelMongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoFactionsEntity {
    private ObjectId _id;

    @Singular
    private List<MongoFactionEntity> factions;
}
