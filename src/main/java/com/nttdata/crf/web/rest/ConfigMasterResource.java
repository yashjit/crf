package com.nttdata.crf.web.rest;

import com.nttdata.crf.service.ConfigMasterService;
import com.nttdata.crf.web.rest.errors.BadRequestAlertException;
import com.nttdata.crf.service.dto.ConfigMasterDTO;

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
 * REST controller for managing {@link com.nttdata.crf.domain.ConfigMaster}.
 */
@RestController
@RequestMapping("/api")
public class ConfigMasterResource {

    private final Logger log = LoggerFactory.getLogger(ConfigMasterResource.class);

    private static final String ENTITY_NAME = "configMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConfigMasterService configMasterService;

    public ConfigMasterResource(ConfigMasterService configMasterService) {
        this.configMasterService = configMasterService;
    }

    /**
     * {@code POST  /config-masters} : Create a new configMaster.
     *
     * @param configMasterDTO the configMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new configMasterDTO, or with status {@code 400 (Bad Request)} if the configMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/config-masters")
    public ResponseEntity<ConfigMasterDTO> createConfigMaster(@Valid @RequestBody ConfigMasterDTO configMasterDTO) throws URISyntaxException {
        log.debug("REST request to save ConfigMaster : {}", configMasterDTO);
        if (configMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new configMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfigMasterDTO result = configMasterService.save(configMasterDTO);
        return ResponseEntity.created(new URI("/api/config-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /config-masters} : Updates an existing configMaster.
     *
     * @param configMasterDTO the configMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configMasterDTO,
     * or with status {@code 400 (Bad Request)} if the configMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the configMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/config-masters")
    public ResponseEntity<ConfigMasterDTO> updateConfigMaster(@Valid @RequestBody ConfigMasterDTO configMasterDTO) throws URISyntaxException {
        log.debug("REST request to update ConfigMaster : {}", configMasterDTO);
        if (configMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConfigMasterDTO result = configMasterService.save(configMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, configMasterDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /config-masters} : get all the configMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of configMasters in body.
     */
    @GetMapping("/config-masters")
    public List<ConfigMasterDTO> getAllConfigMasters() {
        log.debug("REST request to get all ConfigMasters");
        return configMasterService.findAll();
    }

    /**
     * {@code GET  /config-masters/:id} : get the "id" configMaster.
     *
     * @param id the id of the configMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/config-masters/{id}")
    public ResponseEntity<ConfigMasterDTO> getConfigMaster(@PathVariable String id) {
        log.debug("REST request to get ConfigMaster : {}", id);
        Optional<ConfigMasterDTO> configMasterDTO = configMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(configMasterDTO);
    }

    /**
     * {@code DELETE  /config-masters/:id} : delete the "id" configMaster.
     *
     * @param id the id of the configMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/config-masters/{id}")
    public ResponseEntity<Void> deleteConfigMaster(@PathVariable String id) {
        log.debug("REST request to delete ConfigMaster : {}", id);
        configMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
