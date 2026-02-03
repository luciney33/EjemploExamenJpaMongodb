package org.example.examenjpamongodbexample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReaderDTO {
    private ObjectId idReader;
    private String nameReader;
    private LocalDate dobReader;
    private CredentialDTO credentialDTO;

}
