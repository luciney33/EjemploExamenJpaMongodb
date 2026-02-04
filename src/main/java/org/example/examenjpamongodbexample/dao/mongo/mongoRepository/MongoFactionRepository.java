package org.example.examenjpamongodbexample.dao.mongo.mongoRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import org.bson.Document;
import org.example.examenjpamongodbexample.dao.mongo.modelMongo.MongoFactionEntity;
import org.example.examenjpamongodbexample.dao.mongo.modelMongo.MongoFactionsEntity;
import org.example.examenjpamongodbexample.dao.utilities.LocalDateAdapter;
import org.example.examenjpamongodbexample.dao.utilities.MongoConnectionPool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MongoFactionRepository {
    private final MongoDatabase dbMongo;

    @Inject
    public MongoFactionRepository(MongoConnectionPool mgConnection) {
        this.dbMongo = mgConnection.getDatabase();
    }

    public List<MongoFactionEntity> getAll(){
        MongoCollection<Document> collection = dbMongo.getCollection("Factions");
        List<MongoFactionsEntity> factions = new ArrayList<>();
        List<Document> documents = collection.find().into(new ArrayList<>());
        
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        
        for (Document faction: documents){
            factions.add((gson.fromJson(faction.toJson(), MongoFactionsEntity.class)));
        }
        List<MongoFactionEntity> factionEntities = new ArrayList<>();
        factions.forEach(faction-> factionEntities.addAll(faction.getFactions()));
        return factionEntities;
    }
}
