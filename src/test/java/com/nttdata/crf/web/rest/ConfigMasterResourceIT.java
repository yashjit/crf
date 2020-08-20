package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.ConfigMaster;
import com.nttdata.crf.repository.ConfigMasterRepository;
import com.nttdata.crf.service.ConfigMasterService;
import com.nttdata.crf.service.dto.ConfigMasterDTO;
import com.nttdata.crf.service.mapper.ConfigMasterMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ConfigMasterResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConfigMasterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_REQUIRED = false;
    private static final Boolean UPDATED_IS_REQUIRED = true;

    private static final String DEFAULT_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM = "BBBBBBBBBB";

    @Autowired
    private ConfigMasterRepository configMasterRepository;

    @Autowired
    private ConfigMasterMapper configMasterMapper;

    @Autowired
    private ConfigMasterService configMasterService;

    @Autowired
    private MockMvc restConfigMasterMockMvc;

    private ConfigMaster configMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigMaster createEntity() {
        ConfigMaster configMaster = new ConfigMaster()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .isRequired(DEFAULT_IS_REQUIRED)
            .custom(DEFAULT_CUSTOM);
        return configMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigMaster createUpdatedEntity() {
        ConfigMaster configMaster = new ConfigMaster()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .isRequired(UPDATED_IS_REQUIRED)
            .custom(UPDATED_CUSTOM);
        return configMaster;
    }

    @BeforeEach
    public void initTest() {
        configMasterRepository.deleteAll();
        configMaster = createEntity();
    }

    @Test
    public void createConfigMaster() throws Exception {
        int databaseSizeBeforeCreate = configMasterRepository.findAll().size();
        // Create the ConfigMaster
        ConfigMasterDTO configMasterDTO = configMasterMapper.toDto(configMaster);
        restConfigMasterMockMvc.perform(post("/api/config-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(configMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfigMaster in the database
        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ConfigMaster testConfigMaster = configMasterList.get(configMasterList.size() - 1);
        assertThat(testConfigMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testConfigMaster.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testConfigMaster.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testConfigMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testConfigMaster.isIsRequired()).isEqualTo(DEFAULT_IS_REQUIRED);
        assertThat(testConfigMaster.getCustom()).isEqualTo(DEFAULT_CUSTOM);
    }

    @Test
    public void createConfigMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configMasterRepository.findAll().size();

        // Create the ConfigMaster with an existing ID
        configMaster.setId("existing_id");
        ConfigMasterDTO configMasterDTO = configMasterMapper.toDto(configMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigMasterMockMvc.perform(post("/api/config-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(configMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigMaster in the database
        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = configMasterRepository.findAll().size();
        // set the field null
        configMaster.setName(null);

        // Create the ConfigMaster, which fails.
        ConfigMasterDTO configMasterDTO = configMasterMapper.toDto(configMaster);


        restConfigMasterMockMvc.perform(post("/api/config-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(configMasterDTO)))
            .andExpect(status().isBadRequest());

        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllConfigMasters() throws Exception {
        // Initialize the database
        configMasterRepository.save(configMaster);

        // Get all the configMasterList
        restConfigMasterMockMvc.perform(get("/api/config-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configMaster.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isRequired").value(hasItem(DEFAULT_IS_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)));
    }
    
    @Test
    public void getConfigMaster() throws Exception {
        // Initialize the database
        configMasterRepository.save(configMaster);

        // Get the configMaster
        restConfigMasterMockMvc.perform(get("/api/config-masters/{id}", configMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(configMaster.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isRequired").value(DEFAULT_IS_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM));
    }
    @Test
    public void getNonExistingConfigMaster() throws Exception {
        // Get the configMaster
        restConfigMasterMockMvc.perform(get("/api/config-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateConfigMaster() throws Exception {
        // Initialize the database
        configMasterRepository.save(configMaster);

        int databaseSizeBeforeUpdate = configMasterRepository.findAll().size();

        // Update the configMaster
        ConfigMaster updatedConfigMaster = configMasterRepository.findById(configMaster.getId()).get();
        updatedConfigMaster
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .isRequired(UPDATED_IS_REQUIRED)
            .custom(UPDATED_CUSTOM);
        ConfigMasterDTO configMasterDTO = configMasterMapper.toDto(updatedConfigMaster);

        restConfigMasterMockMvc.perform(put("/api/config-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(configMasterDTO)))
            .andExpect(status().isOk());

        // Validate the ConfigMaster in the database
        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeUpdate);
        ConfigMaster testConfigMaster = configMasterList.get(configMasterList.size() - 1);
        assertThat(testConfigMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testConfigMaster.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testConfigMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testConfigMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testConfigMaster.isIsRequired()).isEqualTo(UPDATED_IS_REQUIRED);
        assertThat(testConfigMaster.getCustom()).isEqualTo(UPDATED_CUSTOM);
    }

    @Test
    public void updateNonExistingConfigMaster() throws Exception {
        int databaseSizeBeforeUpdate = configMasterRepository.findAll().size();

        // Create the ConfigMaster
        ConfigMasterDTO configMasterDTO = configMasterMapper.toDto(configMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigMasterMockMvc.perform(put("/api/config-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(configMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigMaster in the database
        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteConfigMaster() throws Exception {
        // Initialize the database
        configMasterRepository.save(configMaster);

        int databaseSizeBeforeDelete = configMasterRepository.findAll().size();

        // Delete the configMaster
        restConfigMasterMockMvc.perform(delete("/api/config-masters/{id}", configMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConfigMaster> configMasterList = configMasterRepository.findAll();
        assertThat(configMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
