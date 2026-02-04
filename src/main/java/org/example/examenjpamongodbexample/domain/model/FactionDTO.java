package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactionDTO {
    private String fName;
    private String contact;
    private String planet;
    private int numControlledSystems;
    private LocalDate dateLastPurchase;
    private List<BattleDTO> battlesAsFactionOne;
    private List<BattleDTO> battlesAsFactionTwo ;
    private List<WeaponFactionDTO> weaponsAndFactions;

}
