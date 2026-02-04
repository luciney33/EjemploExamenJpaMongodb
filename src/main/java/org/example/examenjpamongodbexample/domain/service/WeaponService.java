package org.example.examenjpamongodbexample.domain.service;


import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponRepository;
import org.example.examenjpamongodbexample.domain.mapper.MapWeapontoDto;
import org.example.examenjpamongodbexample.domain.model.WeaponDTO;

public class WeaponService {
    private final WeaponRepository weaponRepo;
    private final MapWeapontoDto mapper;

    @Inject
    public WeaponService(WeaponRepository weaponRepo, MapWeapontoDto mapper) {
        this.weaponRepo = weaponRepo;
        this.mapper = mapper;
    }

    public void save(WeaponDTO weaponDTO){
        int savedWeaponId = weaponRepo.save(mapper.dtoToEntity(weaponDTO));
        weaponDTO.setId(savedWeaponId);
        delete(weaponDTO);
    }

    public void delete(WeaponDTO weaponDTO){
        weaponRepo.delete(mapper.dtoToEntity(weaponDTO));
    }
}
