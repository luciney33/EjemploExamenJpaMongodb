package org.example.examenjpamongodbexample.domain.service;


import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;
import org.example.examenjpamongodbexample.domain.mapper.MapWeapontoDto;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;

import java.util.List;

public class WeaponService {
    private final WeaponRepository weaponRepo;
    private final MapWeapontoDto mapper;

    @Inject
    public WeaponService(WeaponRepository weaponRepo, MapWeapontoDto mapper) {
        this.weaponRepo = weaponRepo;
        this.mapper = mapper;
    }

    public List<WeaponDTO> getAll(){
        return mapper.toDTOList(weaponRepo.getAll());
    }

    public void save(WeaponDTO weaponDTO){
        int savedWeaponId = weaponRepo.save(mapper.dtoToEntity(weaponDTO));
        weaponDTO.setId(savedWeaponId);
        delete(weaponDTO);
    }

    public void delete(WeaponDTO weaponDTO){
        weaponRepo.delete(mapper.dtoToEntity(weaponDTO));
    }

    public void update(WeaponDTO weaponDTO){
        // Buscar el arma por nombre en la base de datos
        JpaWeaponEntity weaponEntity = weaponRepo.findByName(weaponDTO.getWName());

        if (weaponEntity != null) {
            // Usar el ID de la base de datos y el nuevo precio del DTO
            weaponEntity.setWPrice(weaponDTO.getWPrice());
            weaponRepo.update(weaponEntity);
        } else {
            throw new RuntimeException("El arma '" + weaponDTO.getWName() + "' no existe en la base de datos");
        }
    }
}
