package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.dao.hibernate.FactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponFactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.WeaponRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaWeaponFactionEntity;
import org.example.examenjpamongodbexample.dao.mongo.modelMongo.MongoFactionEntity;
import org.example.examenjpamongodbexample.dao.mongo.modelMongo.MongoWeaponEntity;
import org.example.examenjpamongodbexample.dao.mongo.mongoRepository.MongoFactionRepository;
import org.example.examenjpamongodbexample.domain.mapper.MapFactiontoDto;
import org.example.examenjpamongodbexample.domain.model.FactionDTO;

import java.util.List;

public class FactionService {
    private final FactionRepository factRepo;
    private final MongoFactionRepository mongoRepo;
    private final WeaponFactionRepository relaRepo;
    private final WeaponRepository weaponRepo;
    private final MapFactiontoDto mapper;

    @Inject
    public FactionService(FactionRepository factRepo, MongoFactionRepository mongoRepo, WeaponFactionRepository relaRepo, WeaponRepository weaponRepo, MapFactiontoDto mapper){
        this.factRepo = factRepo;
        this.mongoRepo = mongoRepo;
        this.relaRepo = relaRepo;
        this.weaponRepo = weaponRepo;
        this.mapper = mapper;
    }

    public List<FactionDTO> getAll(){
        List<MongoFactionEntity> mongoFactions = mongoRepo.getAll();
        for (MongoFactionEntity mongoFactionEntity : mongoFactions) {
            JpaFactionEntity jpaFaction = factRepo.fingByName(mongoFactionEntity.getName());
            if (jpaFaction == null) {
                jpaFaction = new JpaFactionEntity();
                jpaFaction.setFName(mongoFactionEntity.getName());
                jpaFaction.setContact(mongoFactionEntity.getContact());
                jpaFaction.setPlanet(mongoFactionEntity.getPlanet());
                jpaFaction.setNumControlledSystems(mongoFactionEntity.getNumCS());
                jpaFaction.setDateLastPurchase(mongoFactionEntity.getDateLastPurchase());
                factRepo.save(jpaFaction);
            }
            if (mongoFactionEntity.getWeapons() != null) {
                for (MongoWeaponEntity mw : mongoFactionEntity.getWeapons()) {
                    JpaWeaponEntity weapon = weaponRepo.findByName(mw.getName());
                    if (weapon == null) {
                                weapon = new JpaWeaponEntity();
                                weapon.setWName(mw.getName());
                                weapon.setWPrice(mw.getPrice());
                                weaponRepo.save(weapon);
                            }
                    JpaWeaponFactionEntity relation = new JpaWeaponFactionEntity();
                            relation.setFaction(jpaFaction);
                            relation.setWeapon(weapon);
                    relaRepo.save(relation);
                }
            }
        }
        return mapper.toDTOList(factRepo.getAll());

    }
}
