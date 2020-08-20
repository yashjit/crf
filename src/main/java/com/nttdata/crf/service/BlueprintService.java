package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.BlueprintDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Blueprint}.
 */
public interface BlueprintService {

    /**
     * Save a blueprint.
     *
     * @param blueprintDTO the entity to save.
     * @return the persisted entity.
     */
    BlueprintDTO save(BlueprintDTO blueprintDTO);

    /**
     * Get all the blueprints.
     *
     * @return the list of entities.
     */
    List<BlueprintDTO> findAll();


    /**
     * Get the "id" blueprint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BlueprintDTO> findOne(String id);

    /**
     * Delete the "id" blueprint.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
