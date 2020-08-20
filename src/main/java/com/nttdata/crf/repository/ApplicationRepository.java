package com.nttdata.crf.repository;

import com.nttdata.crf.domain.Application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Application entity.
 */
@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {

    @Query("{}")
    Page<Application> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Application> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Application> findOneWithEagerRelationships(String id);
}
