package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.ApplicationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Application}.
 */
public interface ApplicationService {

    /**
     * Save a application.
     *
     * @param applicationDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationDTO save(ApplicationDTO applicationDTO);

    /**
     * Get all the applications.
     *
     * @return the list of entities.
     */
    List<ApplicationDTO> findAll();

    /**
     * Get all the applications with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ApplicationDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" application.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicationDTO> findOne(String id);

    /**
     * Delete the "id" application.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
