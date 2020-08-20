package com.nttdata.crf.repository;

import com.nttdata.crf.domain.Blueprint;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Blueprint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlueprintRepository extends MongoRepository<Blueprint, String> {
}
