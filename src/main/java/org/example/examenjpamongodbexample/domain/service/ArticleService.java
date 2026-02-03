package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.example.dao.ArticleRepository;
import org.example.dao.NewspaperRepository;
import org.example.dao.ReadArticleRepository;
import org.example.dao.mongoModel.MongoArticleEntity;
import org.example.dao.mongoModel.MongoNewspaperEntity;
import org.example.dao.mongoModel.MongoReadArticleEntity;
import org.example.domain.model.ArticleDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final NewspaperRepository newspaperRepository;
    private final ReadArticleRepository readerArticleRepository;

    @Inject
    public ArticleService(ArticleRepository articleRepository, NewspaperRepository newspaperRepository, ReadArticleRepository readerArticleRepository) {
        this.articleRepository = articleRepository;
        this.newspaperRepository = newspaperRepository;
        this.readerArticleRepository = readerArticleRepository;
    }


    public List<ArticleDTO> getAllArticles() {
        List<MongoArticleEntity> articles = articleRepository.getAll();
        List<MongoNewspaperEntity> newspapers = newspaperRepository.getAll();

        Map<String, ObjectId> articleToNewspaperMap = newspapers.stream().flatMap(newspaper ->
                        newspaper.getArticles().stream().map(article ->
                                Map.entry(article.getDescription(), newspaper.get_id())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        return articles.stream().map(article -> new ArticleDTO(
                0,
                article.getDescription(),
                article.getType(),
                articleToNewspaperMap.get(article.getDescription()),
                avgRating(article)
        )).toList();
    }

    private double avgRating(MongoArticleEntity article) {
        if (article == null || article.getReadArticles() == null || article.getReadArticles().isEmpty()) {
            return 0.0;
        }
        return article.getReadArticles()
                .stream()
                .mapToInt(MongoReadArticleEntity::getRating)
                .average()
                .orElse(0.0);

    }



    public int save(ArticleDTO articleDTO){
        List<MongoReadArticleEntity> readArticles = new ArrayList<>();
        MongoArticleEntity articleEntity = new MongoArticleEntity(
                articleDTO.getName(),
                articleDTO.getTypeDTO(),
                readArticles
        );
        return articleRepository.save(articleEntity, articleDTO.getNpaperId());
    }

    public void delete(String nombre, boolean confirmation, ObjectId newspaperId){
        MongoArticleEntity articleEntity = articleRepository.get(nombre);
        articleRepository.delete(articleEntity, confirmation, newspaperId);
    }

    public void update(ArticleDTO articleDTO, String nombreOriginal){
        List<MongoReadArticleEntity> readArticles = new ArrayList<>();
        MongoArticleEntity articleEntity = new MongoArticleEntity(
                articleDTO.getName(),
                articleDTO.getTypeDTO(),
                readArticles
        );
        articleRepository.update(articleEntity, articleDTO.getNpaperId(), nombreOriginal);
    }
}
