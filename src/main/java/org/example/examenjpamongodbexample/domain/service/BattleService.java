package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.dao.hibernate.BattleRepository;
import org.example.examenjpamongodbexample.dao.hibernate.FactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.SpyRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaBattleEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaSpyEntity;
import org.example.examenjpamongodbexample.domain.mapper.MapBattletoDto;
import org.example.examenjpamongodbexample.domain.mapper.MapFactiontoDto;
import org.example.examenjpamongodbexample.domain.mapper.MapSpytoDto;
import org.example.examenjpamongodbexample.domain.model.BattleDTO;

public class BattleService {
    private final BattleRepository battleRepo;
    private final MapBattletoDto battleMapper;
    private final MapFactiontoDto factionMapper;
    private final MapSpytoDto spyMapper;
    private final FactionRepository factionRepo;
    private final SpyRepository spyRepo;

    @Inject
    public BattleService(BattleRepository battleRepo, MapBattletoDto battleMapper,
                         MapFactiontoDto factionMapper, MapSpytoDto spyMapper,
                         FactionRepository factionRepo, SpyRepository spyRepo) {
        this.battleRepo = battleRepo;
        this.battleMapper = battleMapper;
        this.factionMapper = factionMapper;
        this.spyMapper = spyMapper;
        this.factionRepo = factionRepo;
        this.spyRepo = spyRepo;
    }

    public void save(BattleDTO battleDTO) {
        if (battleDTO.getFactionOne() != null && battleDTO.getFactionTwo() != null) {
            JpaFactionEntity factionOne = factionRepo.fingByName(battleDTO.getFactionOne().getFName());
            if (factionOne == null) {
                factionOne = factionMapper.dtoToEntity(battleDTO.getFactionOne());
                factionRepo.save(factionOne);
            }

            JpaFactionEntity factionTwo = factionRepo.fingByName(battleDTO.getFactionTwo().getFName());
            if (factionTwo == null) {
                factionTwo = factionMapper.dtoToEntity(battleDTO.getFactionTwo());
                factionRepo.save(factionTwo);
            }

            JpaSpyEntity spy = null;
            if (battleDTO.getSpy() != null) {
                spy = spyRepo.findByName(battleDTO.getSpy().getSName());
                if (spy == null) {
                    spy = spyMapper.dtoToEntity(battleDTO.getSpy());
                    spyRepo.save(spy);
                }
            }

            JpaBattleEntity battle = battleMapper.dtoToBattleEntity(battleDTO, factionOne, factionTwo);
            battle.setSpy(spy);
            battleRepo.save(battle);
        }
    }
}
