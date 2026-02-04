package org.example.examenjpamongodbexample.dao.utilities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.common.Configuration;

public class MongoConnectionPool {

    private final Configuration config;
    private MongoClient client;

    @Inject
    public MongoConnectionPool(Configuration config) {
        this.config = config;
    }


    public MongoDatabase getDatabase(){
        String dbName = config.getProperty("databaseName");
        if(client == null){
            String url = config.getProperty("urlDBMongo");
            client = MongoClients.create(url);
            System.out.println("Connected to MongoDB successfully");
            return client.getDatabase(dbName);
        }

        return client.getDatabase(dbName);
    }

    @PreDestroy
    public void close() {
        if (client != null) client.close();
    }
}
