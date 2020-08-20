package com.nttdata.crf.repository;

import com.nttdata.crf.domain.Workload;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Workload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkloadRepository extends MongoRepository<Workload, String> {
}
