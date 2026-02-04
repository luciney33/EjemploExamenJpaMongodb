package org.example.examenjpamongodbexample.domain.mapper;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.domain.model.FactionDTO;

import java.util.ArrayList;
import java.util.List;

public class MapFactiontoDto {
    public List<FactionDTO> toDTOList(List<JpaFactionEntity> entities) {
        List<FactionDTO> factionDTOS = new ArrayList<>();
        for (JpaFactionEntity entity : entities) {
            factionDTOS.add(toDTO(entity));
        }
        return factionDTOS;
    }

    public FactionDTO toDTO(JpaFactionEntity entity) {
        return new FactionDTO(entity.getFName(), entity.getContact(),
                entity.getPlanet(), entity.getNumControlledSystems(), entity.getDateLastPurchase(), null,null,null);
    }

    public JpaFactionEntity dtoToEntity(FactionDTO dto) {
        JpaFactionEntity entity = new JpaFactionEntity();
        entity.setFName(dto.getFName());
        entity.setContact(dto.getContact());
        entity.setPlanet(dto.getPlanet());
        entity.setNumControlledSystems(dto.getNumControlledSystems());
        entity.setDateLastPurchase(dto.getDateLastPurchase());
        return entity;
    }
}
