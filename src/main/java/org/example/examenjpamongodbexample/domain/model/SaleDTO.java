package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private int id;
    private WeaponFactionDTO weaponFaction;
    private String units;
    private LocalDate saleDate;
}
