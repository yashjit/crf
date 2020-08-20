package com.nttdata.crf.repository;

import com.nttdata.crf.domain.FieldMaster;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the FieldMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldMasterRepository extends MongoRepository<FieldMaster, String> {
}
