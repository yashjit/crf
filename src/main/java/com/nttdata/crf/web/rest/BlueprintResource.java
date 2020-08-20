package com.nttdata.crf.web.rest;

import com.nttdata.crf.service.BlueprintService;
import com.nttdata.crf.web.rest.errors.BadRequestAlertException;
import com.nttdata.crf.service.dto.BlueprintDTO;

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
 * REST controller for managing {@link com.nttdata.crf.domain.Blueprint}.
 */
@RestController
@RequestMapping("/api")
public class BlueprintResource {

    private final Logger log = LoggerFactory.getLogger(BlueprintResource.class);

    private static final String ENTITY_NAME = "blueprint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlueprintService blueprintService;

    public BlueprintResource(BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    /**
     * {@code POST  /blueprints} : Create a new blueprint.
     *
     * @param blueprintDTO the blueprintDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blueprintDTO, or with status {@code 400 (Bad Request)} if the blueprint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blueprints")
    public ResponseEntity<BlueprintDTO> createBlueprint(@Valid @RequestBody BlueprintDTO blueprintDTO) throws URISyntaxException {
        log.debug("REST request to save Blueprint : {}", blueprintDTO);
        if (blueprintDTO.getId() != null) {
            throw new BadRequestAlertException("A new blueprint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlueprintDTO result = blueprintService.save(blueprintDTO);
        return ResponseEntity.created(new URI("/api/blueprints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /blueprints} : Updates an existing blueprint.
     *
     * @param blueprintDTO the blueprintDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blueprintDTO,
     * or with status {@code 400 (Bad Request)} if the blueprintDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blueprintDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blueprints")
    public ResponseEntity<BlueprintDTO> updateBlueprint(@Valid @RequestBody BlueprintDTO blueprintDTO) throws URISyntaxException {
        log.debug("REST request to update Blueprint : {}", blueprintDTO);
        if (blueprintDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlueprintDTO result = blueprintService.save(blueprintDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blueprintDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /blueprints} : get all the blueprints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blueprints in body.
     */
    @GetMapping("/blueprints")
    public List<BlueprintDTO> getAllBlueprints() {
        log.debug("REST request to get all Blueprints");
        return blueprintService.findAll();
    }

    /**
     * {@code GET  /blueprints/:id} : get the "id" blueprint.
     *
     * @param id the id of the blueprintDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blueprintDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blueprints/{id}")
    public ResponseEntity<BlueprintDTO> getBlueprint(@PathVariable String id) {
        log.debug("REST request to get Blueprint : {}", id);
        Optional<BlueprintDTO> blueprintDTO = blueprintService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blueprintDTO);
    }

    /**
     * {@code DELETE  /blueprints/:id} : delete the "id" blueprint.
     *
     * @param id the id of the blueprintDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blueprints/{id}")
    public ResponseEntity<Void> deleteBlueprint(@PathVariable String id) {
        log.debug("REST request to delete Blueprint : {}", id);
        blueprintService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
