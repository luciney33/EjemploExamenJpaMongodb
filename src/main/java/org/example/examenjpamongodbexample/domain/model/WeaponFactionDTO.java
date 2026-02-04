package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeaponFactionDTO {
    private int id;
    private FactionDTO faction;
    private WeaponDTO weapon;
    private List<SaleDTO> sales;

}
