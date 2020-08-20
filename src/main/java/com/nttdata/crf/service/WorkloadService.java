package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.WorkloadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Workload}.
 */
public interface WorkloadService {

    /**
     * Save a workload.
     *
     * @param workloadDTO the entity to save.
     * @return the persisted entity.
     */
    WorkloadDTO save(WorkloadDTO workloadDTO);

    /**
     * Get all the workloads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkloadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workload.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkloadDTO> findOne(String id);

    /**
     * Delete the "id" workload.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
