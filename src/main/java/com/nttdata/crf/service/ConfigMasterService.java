package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.ConfigMasterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.ConfigMaster}.
 */
public interface ConfigMasterService {

    /**
     * Save a configMaster.
     *
     * @param configMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ConfigMasterDTO save(ConfigMasterDTO configMasterDTO);

    /**
     * Get all the configMasters.
     *
     * @return the list of entities.
     */
    List<ConfigMasterDTO> findAll();


    /**
     * Get the "id" configMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConfigMasterDTO> findOne(String id);

    /**
     * Delete the "id" configMaster.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
