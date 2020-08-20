package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.WaveDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Wave}.
 */
public interface WaveService {

    /**
     * Save a wave.
     *
     * @param waveDTO the entity to save.
     * @return the persisted entity.
     */
    WaveDTO save(WaveDTO waveDTO);

    /**
     * Get all the waves.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WaveDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wave.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WaveDTO> findOne(String id);

    /**
     * Delete the "id" wave.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
