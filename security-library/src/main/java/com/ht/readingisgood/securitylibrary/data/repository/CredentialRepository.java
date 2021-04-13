package com.ht.readingisgood.securitylibrary.data.repository;

import com.ht.readingisgood.securitylibrary.data.model.CredentialEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<CredentialEntity, String> {
}