package org.example.examenjpamongodbexample.domain.mapper;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaSpyEntity;
import org.example.examenjpamongodbexample.domain.model.SpyDTO;

import java.util.ArrayList;
import java.util.List;

public class MapSpytoDto {
    public List<SpyDTO> toDTOList(List<JpaSpyEntity> entities) {
        List<SpyDTO> spyDTOS = new ArrayList<>();
        for (JpaSpyEntity entity : entities) {
            spyDTOS.add(toDTO(entity));
        }
        return spyDTOS;
    }

    public SpyDTO toDTO(JpaSpyEntity entity) {
        return new SpyDTO(
            entity.getId(),
            entity.getSName(),
            entity.getSRace(),
            null
        );
    }

    public JpaSpyEntity dtoToEntity(SpyDTO dto) {
        JpaSpyEntity entity = new JpaSpyEntity();
        entity.setId(dto.getId());
        entity.setSName(dto.getSName());
        entity.setSRace(dto.getSRace());
        return entity;
    }
}

