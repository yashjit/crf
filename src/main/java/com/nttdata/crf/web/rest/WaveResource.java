package com.nttdata.crf.web.rest;

import com.nttdata.crf.service.WaveService;
import com.nttdata.crf.web.rest.errors.BadRequestAlertException;
import com.nttdata.crf.service.dto.WaveDTO;

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
 * REST controller for managing {@link com.nttdata.crf.domain.Wave}.
 */
@RestController
@RequestMapping("/api")
public class WaveResource {

    private final Logger log = LoggerFactory.getLogger(WaveResource.class);

    private static final String ENTITY_NAME = "wave";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaveService waveService;

    public WaveResource(WaveService waveService) {
        this.waveService = waveService;
    }

    /**
     * {@code POST  /waves} : Create a new wave.
     *
     * @param waveDTO the waveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waveDTO, or with status {@code 400 (Bad Request)} if the wave has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/waves")
    public ResponseEntity<WaveDTO> createWave(@Valid @RequestBody WaveDTO waveDTO) throws URISyntaxException {
        log.debug("REST request to save Wave : {}", waveDTO);
        if (waveDTO.getId() != null) {
            throw new BadRequestAlertException("A new wave cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaveDTO result = waveService.save(waveDTO);
        return ResponseEntity.created(new URI("/api/waves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /waves} : Updates an existing wave.
     *
     * @param waveDTO the waveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waveDTO,
     * or with status {@code 400 (Bad Request)} if the waveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/waves")
    public ResponseEntity<WaveDTO> updateWave(@Valid @RequestBody WaveDTO waveDTO) throws URISyntaxException {
        log.debug("REST request to update Wave : {}", waveDTO);
        if (waveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaveDTO result = waveService.save(waveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waveDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /waves} : get all the waves.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waves in body.
     */
    @GetMapping("/waves")
    public ResponseEntity<List<WaveDTO>> getAllWaves(Pageable pageable) {
        log.debug("REST request to get a page of Waves");
        Page<WaveDTO> page = waveService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /waves/:id} : get the "id" wave.
     *
     * @param id the id of the waveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/waves/{id}")
    public ResponseEntity<WaveDTO> getWave(@PathVariable String id) {
        log.debug("REST request to get Wave : {}", id);
        Optional<WaveDTO> waveDTO = waveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(waveDTO);
    }

    /**
     * {@code DELETE  /waves/:id} : delete the "id" wave.
     *
     * @param id the id of the waveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/waves/{id}")
    public ResponseEntity<Void> deleteWave(@PathVariable String id) {
        log.debug("REST request to delete Wave : {}", id);
        waveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
