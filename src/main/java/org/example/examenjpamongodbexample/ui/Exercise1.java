package org.example.examenjpamongodbexample.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.examenjpamongodbexample.domain.error.DatabaseError;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;
import org.example.examenjpamongodbexample.domain.service.WeaponService;
import java.util.Locale;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            WeaponService weaponService = container.select(WeaponService.class).get();
            try {
                System.out.println("Introduce el nombre del weapon");
                String name = sc.nextLine();
                System.out.println("Introduce el precio");
                Double precio = sc.nextDouble();
                WeaponDTO weaponDTO = new WeaponDTO(0,name,precio);
                weaponService.save(weaponDTO);
                System.out.println("Guardado correctamente");
            } catch (DatabaseError e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
