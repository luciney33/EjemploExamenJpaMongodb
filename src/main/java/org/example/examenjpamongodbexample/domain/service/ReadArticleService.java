package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.dao.ReadArticleRepository;
import org.example.dao.ReaderRepository;
import org.example.dao.mongoModel.MongoReadArticleEntity;
import org.example.domain.model.ReaderArticleDTO;

import java.util.List;


public class ReadArticleService {
    private final ReadArticleRepository readArticleRepository;
    private final ReaderRepository readerRepository;

    @Inject
    public ReadArticleService(ReadArticleRepository readArticleRepository, ReaderRepository readerRepository) {
        this.readArticleRepository = readArticleRepository;
        this.readerRepository = readerRepository;
    }

    public int addRating(ReaderArticleDTO readerArticleDTO) {
        MongoReadArticleEntity readArticleEntity = new MongoReadArticleEntity(
                readerArticleDTO.getIdReader(),
                readerArticleDTO.getRating()
        );

        return readArticleRepository.save(readArticleEntity, readerArticleDTO.getIdArticle());
    }


    public void updateReadArticle(ReaderArticleDTO readerArticleDTO) {
        MongoReadArticleEntity entity = new MongoReadArticleEntity(
                readerArticleDTO.getIdReader(),
                readerArticleDTO.getRating()
        );
        readArticleRepository.update(entity, readerArticleDTO.getIdArticle());
    }


    public List<ReaderArticleDTO> getAllByArticle(String articleId){
        List<MongoReadArticleEntity> readArticles = readArticleRepository.getAllByArticle(articleId);
        return readArticles.stream().map(readArticle -> {
            String readerName = readerRepository.get(readArticle.get_id()).getName();
            return new ReaderArticleDTO(
                    articleId,
                    readArticle.get_id(),
                    readerName,
                    null,
                    null,
                    readArticle.getRating()
            );
        }).toList();
    }



    public boolean delete(ReaderArticleDTO readerArticleDTO) {
        readArticleRepository.delete(readerArticleDTO.getIdReader(), readerArticleDTO.getIdArticle());
        return true;
    }
}
