package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.FieldMasterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.FieldMaster}.
 */
public interface FieldMasterService {

    /**
     * Save a fieldMaster.
     *
     * @param fieldMasterDTO the entity to save.
     * @return the persisted entity.
     */
    FieldMasterDTO save(FieldMasterDTO fieldMasterDTO);

    /**
     * Get all the fieldMasters.
     *
     * @return the list of entities.
     */
    List<FieldMasterDTO> findAll();


    /**
     * Get the "id" fieldMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldMasterDTO> findOne(String id);

    /**
     * Delete the "id" fieldMaster.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
