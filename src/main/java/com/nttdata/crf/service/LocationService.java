package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.LocationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Location}.
 */
public interface LocationService {

    /**
     * Save a location.
     *
     * @param locationDTO the entity to save.
     * @return the persisted entity.
     */
    LocationDTO save(LocationDTO locationDTO);

    /**
     * Get all the locations.
     *
     * @return the list of entities.
     */
    List<LocationDTO> findAll();


    /**
     * Get the "id" location.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LocationDTO> findOne(String id);

    /**
     * Delete the "id" location.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
