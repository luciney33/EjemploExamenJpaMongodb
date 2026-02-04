package org.example.examenjpamongodbexample.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.examenjpamongodbexample.domain.error.DatabaseError;
import org.example.examenjpamongodbexample.domain.model.FactionDTO;
import org.example.examenjpamongodbexample.domain.service.FactionService;

import java.util.List;

public class Exercise0 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            FactionService factionService = container.select(FactionService.class).get();
            try {
                List<FactionDTO> factions = factionService.getAll();
                System.out.println("\n✓ Factions loaded successfully from MongoDB to JPA");
                System.out.println("\n=== FACCIONES CARGADAS ===");
                System.out.println("Total de facciones: " + factions.size());
                System.out.println("\nDetalle de facciones:");
                factions.forEach(faction -> System.out.println("\n" + faction));

            } catch (DatabaseError e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
