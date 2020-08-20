package com.nttdata.crf.service;

import com.nttdata.crf.service.dto.ProjectDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.nttdata.crf.domain.Project}.
 */
public interface ProjectService {

    /**
     * Save a project.
     *
     * @param projectDTO the entity to save.
     * @return the persisted entity.
     */
    ProjectDTO save(ProjectDTO projectDTO);

    /**
     * Get all the projects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectDTO> findAll(Pageable pageable);


    /**
     * Get the "id" project.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectDTO> findOne(String id);

    /**
     * Delete the "id" project.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
