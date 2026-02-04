package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.examenjpamongodbexample.dao.hibernate.BattleRepository;
import org.example.examenjpamongodbexample.dao.hibernate.FactionRepository;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaBattleEntity;
import org.example.examenjpamongodbexample.dao.hibernate.model.JpaFactionEntity;
import org.example.examenjpamongodbexample.domain.mapper.MapBattletoDto;
import org.example.examenjpamongodbexample.domain.mapper.MapFactiontoDto;
import org.example.examenjpamongodbexample.domain.model.BattleDTO;

public class BattleService {
    private final BattleRepository battleRepo;
    private final MapBattletoDto battleMapper;
    private final MapFactiontoDto factionMapper;
    private final FactionRepository factionRepo;

    @Inject
    public BattleService(BattleRepository battleRepo, MapBattletoDto battleMapper,
                         MapFactiontoDto factionMapper, FactionRepository factionRepo) {
        this.battleRepo = battleRepo;
        this.battleMapper = battleMapper;
        this.factionMapper = factionMapper;
        this.factionRepo = factionRepo;
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

            JpaBattleEntity battle = battleMapper.dtoToBattleEntity(battleDTO, factionOne, factionTwo);
            battleRepo.save(battle);
        }
    }
}


