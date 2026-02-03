package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.dao.NewspaperRepository;
import org.example.dao.mongoModel.MongoNewspaperEntity;
import org.example.domain.model.NewspaperDTO;

import java.util.List;

public class NewspaperService {
    private final NewspaperRepository newspaperRepository;

    @Inject
    public NewspaperService(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    public List<NewspaperDTO> getAllNewspaper(){
        List<MongoNewspaperEntity> newsEntity = newspaperRepository.getAll();

        return newsEntity.stream().map(newspaperEntity -> new NewspaperDTO(
                newspaperEntity.get_id(),
                newspaperEntity.getName()
        )).toList();
    }
}
