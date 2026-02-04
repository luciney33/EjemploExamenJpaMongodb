package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BattleDTO {
    private int id;
    private String bName;
    private FactionDTO factionOne;
    private FactionDTO factionTwo;
    private String bPlace;
    private LocalDate bDate;
    private SpyDTO spy;

}
