package org.example.examenjpamongodbexample.domain.mapper;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaBattleEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.domain.model.BattleDTO;


public class MapBattletoDto {

    public JpaBattleEntity dtoToBattleEntity(BattleDTO dto, JpaFactionEntity factionOne, JpaFactionEntity factionTwo) {
        JpaBattleEntity entity = new JpaBattleEntity();
        entity.setId(dto.getId());
        entity.setBName(dto.getBName());
        entity.setBPlace(dto.getBPlace());
        entity.setBDate(dto.getBDate());
        entity.setFactionOne(factionOne);
        entity.setFactionTwo(factionTwo);
        return entity;
    }
}
