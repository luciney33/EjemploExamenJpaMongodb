package org.example.examenjpamongodbexample.domain.service;

import jakarta.inject.Inject;
import org.example.dao.CredentialRepository;
import org.example.dao.jpaModel.JPACredentialEntity;
import org.example.domain.model.CredentialDTO;

public class CredentialService {
    private final CredentialRepository credentialRepository;

    @Inject
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean checkLogin(CredentialDTO credentialDTO) {
        JPACredentialEntity credentialEntity = credentialRepository.get(credentialDTO.getUsername());

        return credentialEntity.getPassword().equals(credentialDTO.getPassword());

    }
}
