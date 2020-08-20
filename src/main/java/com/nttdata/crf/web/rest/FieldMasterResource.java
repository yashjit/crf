package com.nttdata.crf.web.rest;

import com.nttdata.crf.service.FieldMasterService;
import com.nttdata.crf.web.rest.errors.BadRequestAlertException;
import com.nttdata.crf.service.dto.FieldMasterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nttdata.crf.domain.FieldMaster}.
 */
@RestController
@RequestMapping("/api")
public class FieldMasterResource {

    private final Logger log = LoggerFactory.getLogger(FieldMasterResource.class);

    private static final String ENTITY_NAME = "fieldMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldMasterService fieldMasterService;

    public FieldMasterResource(FieldMasterService fieldMasterService) {
        this.fieldMasterService = fieldMasterService;
    }

    /**
     * {@code POST  /field-masters} : Create a new fieldMaster.
     *
     * @param fieldMasterDTO the fieldMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldMasterDTO, or with status {@code 400 (Bad Request)} if the fieldMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-masters")
    public ResponseEntity<FieldMasterDTO> createFieldMaster(@Valid @RequestBody FieldMasterDTO fieldMasterDTO) throws URISyntaxException {
        log.debug("REST request to save FieldMaster : {}", fieldMasterDTO);
        if (fieldMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldMasterDTO result = fieldMasterService.save(fieldMasterDTO);
        return ResponseEntity.created(new URI("/api/field-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-masters} : Updates an existing fieldMaster.
     *
     * @param fieldMasterDTO the fieldMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldMasterDTO,
     * or with status {@code 400 (Bad Request)} if the fieldMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-masters")
    public ResponseEntity<FieldMasterDTO> updateFieldMaster(@Valid @RequestBody FieldMasterDTO fieldMasterDTO) throws URISyntaxException {
        log.debug("REST request to update FieldMaster : {}", fieldMasterDTO);
        if (fieldMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldMasterDTO result = fieldMasterService.save(fieldMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldMasterDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /field-masters} : get all the fieldMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldMasters in body.
     */
    @GetMapping("/field-masters")
    public List<FieldMasterDTO> getAllFieldMasters() {
        log.debug("REST request to get all FieldMasters");
        return fieldMasterService.findAll();
    }

    /**
     * {@code GET  /field-masters/:id} : get the "id" fieldMaster.
     *
     * @param id the id of the fieldMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-masters/{id}")
    public ResponseEntity<FieldMasterDTO> getFieldMaster(@PathVariable String id) {
        log.debug("REST request to get FieldMaster : {}", id);
        Optional<FieldMasterDTO> fieldMasterDTO = fieldMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldMasterDTO);
    }

    /**
     * {@code DELETE  /field-masters/:id} : delete the "id" fieldMaster.
     *
     * @param id the id of the fieldMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-masters/{id}")
    public ResponseEntity<Void> deleteFieldMaster(@PathVariable String id) {
        log.debug("REST request to delete FieldMaster : {}", id);
        fieldMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
