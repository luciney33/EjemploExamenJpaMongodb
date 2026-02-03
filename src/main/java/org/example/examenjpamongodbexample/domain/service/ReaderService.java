package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.example.dao.*;
import org.example.dao.jpaModel.*;
import org.example.dao.mongoModel.MongoNewspaperEntity;
import org.example.dao.mongoModel.MongoReaderEntity;
import org.example.dao.mongoModel.MongoSubscriptionEntity;
import org.example.domain.model.ReaderArticleDTO;
import org.example.domain.model.ReaderDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReaderService {
    private final ReaderRepository readerRepository;
    private final CredentialRepository credentialRepository;
    private final ReadArticleRepository readArticleRepository;
    private final NewspaperRepository newspaperRepository;


    @Inject
    public ReaderService(ReaderRepository readerRepository, ReadArticleRepository readArticleRepository, CredentialRepository credentialRepository, NewspaperRepository newspaperRepository) {
        this.readerRepository = readerRepository;
        this.readArticleRepository = readArticleRepository;
        this.credentialRepository = credentialRepository;
        this.newspaperRepository = newspaperRepository;
    }

    public List<ReaderDTO> getAllReaders(){
        List<MongoReaderEntity> readerEntity = readerRepository.getAll();
        return readerEntity.stream().map(readerEntity1 -> new ReaderDTO(
                readerEntity1.get_id(),
                readerEntity1.getName(),
                readerEntity1.getDob(),
                null
        )).toList();
    }

    public ReaderDTO getReaderById(ObjectId id){
        MongoReaderEntity readerEntity = readerRepository.get(id);

        return new ReaderDTO(readerEntity.get_id(), readerEntity.getName(), readerEntity.getDob(), null);
    }

    public void deleteReader(ObjectId id){
        MongoReaderEntity readerEntity = readerRepository.get(id);
        readArticleRepository.deleteAllForReader(id);
        readerRepository.delete(readerEntity);
    }


    public List<ReaderArticleDTO> readersOfArticle(String id) {
        List<ReaderArticleDTO> readArticleDTO = new ArrayList<>();

        readerRepository.getByArticleId(id).forEach(reader -> {


            List<String> subs = reader.getSubscriptions().stream()
                    .map(MongoSubscriptionEntity::get_id)
                    .map(newspaperRepository::get)
                    .filter(Objects::nonNull)
                    .map(MongoNewspaperEntity::getName)
                    .distinct()
                    .toList();


            int rating = readArticleRepository.get(reader.get_id(), id).getRating();

            readArticleDTO.add(new ReaderArticleDTO(
                    id,
                    reader.get_id(),
                    reader.getName(),
                    reader.getDob(),
                    subs,
                    rating
            ));
        });
        return readArticleDTO;
    }

    public ObjectId addReader(ReaderDTO readerDTO){

        MongoReaderEntity ReaderEntity = new MongoReaderEntity(
                null,
                readerDTO.getNameReader(),
                readerDTO.getDobReader(),
                new ArrayList<>()
        );

        ObjectId username= (readerRepository.save(ReaderEntity));

       JPACredentialEntity credentialEntity = new JPACredentialEntity(0, username.toString(), readerDTO.getCredentialDTO().getPassword(), null);
        credentialRepository.save(credentialEntity);
        return username;

    }
}
