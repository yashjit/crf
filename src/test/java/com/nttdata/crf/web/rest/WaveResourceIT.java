package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.Wave;
import com.nttdata.crf.repository.WaveRepository;
import com.nttdata.crf.service.WaveService;
import com.nttdata.crf.service.dto.WaveDTO;
import com.nttdata.crf.service.mapper.WaveMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WaveResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WaveResourceIT {

    private static final String DEFAULT_WAVE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WAVE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WaveRepository waveRepository;

    @Autowired
    private WaveMapper waveMapper;

    @Autowired
    private WaveService waveService;

    @Autowired
    private MockMvc restWaveMockMvc;

    private Wave wave;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wave createEntity() {
        Wave wave = new Wave()
            .waveName(DEFAULT_WAVE_NAME)
            .fileName(DEFAULT_FILE_NAME)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .status(DEFAULT_STATUS)
            .custom(DEFAULT_CUSTOM)
            .createdBy(DEFAULT_CREATED_BY)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return wave;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wave createUpdatedEntity() {
        Wave wave = new Wave()
            .waveName(UPDATED_WAVE_NAME)
            .fileName(UPDATED_FILE_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .custom(UPDATED_CUSTOM)
            .createdBy(UPDATED_CREATED_BY)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        return wave;
    }

    @BeforeEach
    public void initTest() {
        waveRepository.deleteAll();
        wave = createEntity();
    }

    @Test
    public void createWave() throws Exception {
        int databaseSizeBeforeCreate = waveRepository.findAll().size();
        // Create the Wave
        WaveDTO waveDTO = waveMapper.toDto(wave);
        restWaveMockMvc.perform(post("/api/waves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waveDTO)))
            .andExpect(status().isCreated());

        // Validate the Wave in the database
        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeCreate + 1);
        Wave testWave = waveList.get(waveList.size() - 1);
        assertThat(testWave.getWaveName()).isEqualTo(DEFAULT_WAVE_NAME);
        assertThat(testWave.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testWave.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testWave.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testWave.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWave.getCustom()).isEqualTo(DEFAULT_CUSTOM);
        assertThat(testWave.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testWave.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testWave.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    public void createWaveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waveRepository.findAll().size();

        // Create the Wave with an existing ID
        wave.setId("existing_id");
        WaveDTO waveDTO = waveMapper.toDto(wave);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaveMockMvc.perform(post("/api/waves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wave in the database
        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkWaveNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = waveRepository.findAll().size();
        // set the field null
        wave.setWaveName(null);

        // Create the Wave, which fails.
        WaveDTO waveDTO = waveMapper.toDto(wave);


        restWaveMockMvc.perform(post("/api/waves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waveDTO)))
            .andExpect(status().isBadRequest());

        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllWaves() throws Exception {
        // Initialize the database
        waveRepository.save(wave);

        // Get all the waveList
        restWaveMockMvc.perform(get("/api/waves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wave.getId())))
            .andExpect(jsonPath("$.[*].waveName").value(hasItem(DEFAULT_WAVE_NAME)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    public void getWave() throws Exception {
        // Initialize the database
        waveRepository.save(wave);

        // Get the wave
        restWaveMockMvc.perform(get("/api/waves/{id}", wave.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wave.getId()))
            .andExpect(jsonPath("$.waveName").value(DEFAULT_WAVE_NAME))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }
    @Test
    public void getNonExistingWave() throws Exception {
        // Get the wave
        restWaveMockMvc.perform(get("/api/waves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWave() throws Exception {
        // Initialize the database
        waveRepository.save(wave);

        int databaseSizeBeforeUpdate = waveRepository.findAll().size();

        // Update the wave
        Wave updatedWave = waveRepository.findById(wave.getId()).get();
        updatedWave
            .waveName(UPDATED_WAVE_NAME)
            .fileName(UPDATED_FILE_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .custom(UPDATED_CUSTOM)
            .createdBy(UPDATED_CREATED_BY)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        WaveDTO waveDTO = waveMapper.toDto(updatedWave);

        restWaveMockMvc.perform(put("/api/waves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waveDTO)))
            .andExpect(status().isOk());

        // Validate the Wave in the database
        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeUpdate);
        Wave testWave = waveList.get(waveList.size() - 1);
        assertThat(testWave.getWaveName()).isEqualTo(UPDATED_WAVE_NAME);
        assertThat(testWave.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testWave.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testWave.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testWave.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWave.getCustom()).isEqualTo(UPDATED_CUSTOM);
        assertThat(testWave.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testWave.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testWave.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    public void updateNonExistingWave() throws Exception {
        int databaseSizeBeforeUpdate = waveRepository.findAll().size();

        // Create the Wave
        WaveDTO waveDTO = waveMapper.toDto(wave);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaveMockMvc.perform(put("/api/waves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wave in the database
        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteWave() throws Exception {
        // Initialize the database
        waveRepository.save(wave);

        int databaseSizeBeforeDelete = waveRepository.findAll().size();

        // Delete the wave
        restWaveMockMvc.perform(delete("/api/waves/{id}", wave.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Wave> waveList = waveRepository.findAll();
        assertThat(waveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
