package org.example.examenjpamongodbexample.ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.examenjpamongodbexample.domain.model.BattleDTO;
import org.example.examenjpamongodbexample.domain.model.FactionDTO;
import org.example.examenjpamongodbexample.domain.model.SpyDTO;
import org.example.examenjpamongodbexample.domain.service.BattleService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();

        try (SeContainer container = initializer.initialize()) {
            BattleService battleService = container.select(BattleService.class).get();

            System.out.println("=== Insertar Nueva Batalla ===");

            System.out.println("Introduce el nombre de la batalla:");
            String battleName = sc.nextLine();

            System.out.println("Introduce el lugar de la batalla:");
            String battlePlace = sc.nextLine();

            System.out.println("Introduce la fecha de la batalla (YYYY-MM-DD):");
            String dateStr = sc.nextLine();
            LocalDate battleDate = LocalDate.parse(dateStr);

            System.out.println("\n--- Espía (Spy) ---");
            System.out.println("Introduce el nombre del espía:");
            String spyName = sc.nextLine();

            System.out.println("Introduce la raza del espía:");
            String spyRace = sc.nextLine();

            SpyDTO spy = new SpyDTO(0, spyName, spyRace, null);

            System.out.println("\n--- Primera Facción ---");
            System.out.println("Introduce el nombre de la facción:");
            String faction1Name = sc.nextLine();

            System.out.println("Introduce el contacto:");
            String faction1Contact = sc.nextLine();

            System.out.println("Introduce el planeta:");
            String faction1Planet = sc.nextLine();

            System.out.println("Introduce el número de sistemas controlados:");
            int faction1NumSystems = sc.nextInt();
            sc.nextLine();

            System.out.println("Introduce la fecha de última compra (YYYY-MM-DD):");
            String faction1DateStr = sc.nextLine();
            LocalDate faction1Date = LocalDate.parse(faction1DateStr);

            FactionDTO factionOne = new FactionDTO(
                faction1Name,
                faction1Contact,
                faction1Planet,
                faction1NumSystems,
                faction1Date,
                null,
                null,
                null
            );

            System.out.println("\n--- Segunda Facción ---");
            System.out.println("Introduce el nombre de la facción:");
            String faction2Name = sc.nextLine();

            System.out.println("Introduce el contacto:");
            String faction2Contact = sc.nextLine();

            System.out.println("Introduce el planeta:");
            String faction2Planet = sc.nextLine();

            System.out.println("Introduce el número de sistemas controlados:");
            int faction2NumSystems = sc.nextInt();
            sc.nextLine();

            System.out.println("Introduce la fecha de última compra (YYYY-MM-DD):");
            String faction2DateStr = sc.nextLine();
            LocalDate faction2Date = LocalDate.parse(faction2DateStr);

            FactionDTO factionTwo = new FactionDTO(
                faction2Name,
                faction2Contact,
                faction2Planet,
                faction2NumSystems,
                faction2Date,
                null,
                null,
                null
            );

            BattleDTO battleDTO = new BattleDTO(
                0,
                battleName,
                factionOne,
                factionTwo,
                battlePlace,
                battleDate,
                spy
            );

            battleService.save(battleDTO);

            System.out.println("\n✓ Batalla guardada correctamente");
            System.out.println("Las facciones y el espía se han creado o actualizado automáticamente");

        } catch (Exception e) {
            System.err.println("Error al guardar la batalla: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}

