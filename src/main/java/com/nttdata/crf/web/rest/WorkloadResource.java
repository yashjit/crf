package com.nttdata.crf.web.rest;

import com.nttdata.crf.service.WorkloadService;
import com.nttdata.crf.web.rest.errors.BadRequestAlertException;
import com.nttdata.crf.service.dto.WorkloadDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nttdata.crf.domain.Workload}.
 */
@RestController
@RequestMapping("/api")
public class WorkloadResource {

    private final Logger log = LoggerFactory.getLogger(WorkloadResource.class);

    private static final String ENTITY_NAME = "workload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkloadService workloadService;

    public WorkloadResource(WorkloadService workloadService) {
        this.workloadService = workloadService;
    }

    /**
     * {@code POST  /workloads} : Create a new workload.
     *
     * @param workloadDTO the workloadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workloadDTO, or with status {@code 400 (Bad Request)} if the workload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workloads")
    public ResponseEntity<WorkloadDTO> createWorkload(@Valid @RequestBody WorkloadDTO workloadDTO) throws URISyntaxException {
        log.debug("REST request to save Workload : {}", workloadDTO);
        if (workloadDTO.getId() != null) {
            throw new BadRequestAlertException("A new workload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkloadDTO result = workloadService.save(workloadDTO);
        return ResponseEntity.created(new URI("/api/workloads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /workloads} : Updates an existing workload.
     *
     * @param workloadDTO the workloadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workloadDTO,
     * or with status {@code 400 (Bad Request)} if the workloadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workloadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workloads")
    public ResponseEntity<WorkloadDTO> updateWorkload(@Valid @RequestBody WorkloadDTO workloadDTO) throws URISyntaxException {
        log.debug("REST request to update Workload : {}", workloadDTO);
        if (workloadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkloadDTO result = workloadService.save(workloadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workloadDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /workloads} : get all the workloads.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workloads in body.
     */
    @GetMapping("/workloads")
    public ResponseEntity<List<WorkloadDTO>> getAllWorkloads(Pageable pageable) {
        log.debug("REST request to get a page of Workloads");
        Page<WorkloadDTO> page = workloadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workloads/:id} : get the "id" workload.
     *
     * @param id the id of the workloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workloadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workloads/{id}")
    public ResponseEntity<WorkloadDTO> getWorkload(@PathVariable String id) {
        log.debug("REST request to get Workload : {}", id);
        Optional<WorkloadDTO> workloadDTO = workloadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workloadDTO);
    }

    /**
     * {@code DELETE  /workloads/:id} : delete the "id" workload.
     *
     * @param id the id of the workloadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workloads/{id}")
    public ResponseEntity<Void> deleteWorkload(@PathVariable String id) {
        log.debug("REST request to delete Workload : {}", id);
        workloadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
