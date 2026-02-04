package org.example.examenjpamongodbexample.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.examenjpamongodbexample.domain.error.DatabaseError;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;
import org.example.examenjpamongodbexample.domain.service.WeaponService;

import java.util.Locale;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();

        try (SeContainer container = initializer.initialize()) {
            WeaponService weaponService = container.select(WeaponService.class).get();

            try {
                System.out.println("=== Actualizar Precio de Arma ===");
                System.out.println("Introduce el nombre del arma:");
                String weaponName = sc.nextLine();
                System.out.println("Introduce el nuevo precio:");
                Double newPrice = sc.nextDouble();
                WeaponDTO weaponDTO = new WeaponDTO(0, weaponName, newPrice);
                weaponService.update(weaponDTO);
                System.out.println("\nPrecio actualizado correctamente");
            } catch (DatabaseError e) {
                System.err.println("Error de base de datos: " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error al actualizar el precio: " + e.getMessage());
                e.printStackTrace();
            }
        } finally {
            sc.close();
        }
    }
}

