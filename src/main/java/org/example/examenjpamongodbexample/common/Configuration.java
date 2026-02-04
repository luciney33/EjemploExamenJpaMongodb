package org.example.examenjpamongodbexample.common;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@ApplicationScoped
public class Configuration {

    private static Configuration instance = null;
    private Properties p;

    public Configuration() {
        Path p1 = Paths.get("src/main/resources/properties/mysql-properties.xml");
        p = new Properties();
        InputStream propertiesStream;
        try{
            propertiesStream = Files.newInputStream(p1);
            p.loadFromXML(propertiesStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if(instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String clave){
        return p.getProperty(clave);
    }
}
