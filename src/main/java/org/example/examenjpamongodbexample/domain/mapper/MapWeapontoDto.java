package org.example.examenjpamongodbexample.domain.mapper;

import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;

import java.util.ArrayList;
import java.util.List;

public class MapWeapontoDto {
    public List<WeaponDTO> toDTOList(List<JpaWeaponEntity> entities) {
        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        for (JpaWeaponEntity entity : entities) {
            weaponDTOS.add(toDTO(entity));
        }
        return weaponDTOS;
    }
    public WeaponDTO toDTO(JpaWeaponEntity entity) {
        return new WeaponDTO(entity.getId(), entity.getWName(), entity.getWPrice());
    }

    public JpaWeaponEntity dtoToEntity(WeaponDTO dto) {
        return new JpaWeaponEntity(
                dto.getId(),
                dto.getWName(),
                dto.getWPrice(),
                null
        );
    }
}
