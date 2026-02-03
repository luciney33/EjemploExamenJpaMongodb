package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private int id;
    private String name;
    private String typeDTO;
    private ObjectId npaperId;
    private double avgRating;


}
