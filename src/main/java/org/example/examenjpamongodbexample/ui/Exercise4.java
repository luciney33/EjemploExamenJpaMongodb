package org.example.examenjpamongodbexample.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.examenjpamongodbexample.domain.error.DatabaseError;
import org.example.examenjpamongodbexample.domain.model.FactionDTO;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;
import org.example.examenjpamongodbexample.domain.service.FactionService;
import org.example.examenjpamongodbexample.domain.service.WeaponService;

import java.util.List;

public class Exercise4 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            WeaponService weaponService = container.select(WeaponService.class).get();
            try {
                List<WeaponDTO> weapons = weaponService.getAll();
                System.out.println("\nWeapons loaded successfully");
                System.out.println("Total de facciones: " + weapons.size());
                weapons.forEach(faction -> System.out.println("\n" + faction));
            } catch (DatabaseError e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
