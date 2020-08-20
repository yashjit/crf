package com.nttdata.crf.repository;

import com.nttdata.crf.domain.Wave;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Wave entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaveRepository extends MongoRepository<Wave, String> {
}
