package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpyDTO {
    private int id;
    private String sName;
    private  String sRace;
    private List<BattleDTO> battles;
}
