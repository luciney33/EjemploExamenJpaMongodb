package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.example.dao.ArticleRepository;
import org.example.dao.mongoModel.MongoArticleEntity;
import org.example.domain.model.TypeDTO;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class TypeService {

    private final ArticleRepository articleRepository;

    @Inject
    public TypeService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Menu option 12.
     * Requirement: take all articles, collect their 'type', remove duplicates.
     */
    public List<TypeDTO> getAll() {
        List<MongoArticleEntity> articles = articleRepository.getAll();

        AtomicInteger seq = new AtomicInteger(1);
        return articles.stream()
                .map(MongoArticleEntity::getType)
                .filter(t -> t != null && !t.isBlank())
                .distinct()
                .map(t -> new TypeDTO(seq.getAndIncrement(), t))
                .toList();
    }
}
