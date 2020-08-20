package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.FieldMaster;
import com.nttdata.crf.repository.FieldMasterRepository;
import com.nttdata.crf.service.FieldMasterService;
import com.nttdata.crf.service.dto.FieldMasterDTO;
import com.nttdata.crf.service.mapper.FieldMasterMapper;

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
 * Integration tests for the {@link FieldMasterResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FieldMasterResourceIT {

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
    private FieldMasterRepository fieldMasterRepository;

    @Autowired
    private FieldMasterMapper fieldMasterMapper;

    @Autowired
    private FieldMasterService fieldMasterService;

    @Autowired
    private MockMvc restFieldMasterMockMvc;

    private FieldMaster fieldMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldMaster createEntity() {
        FieldMaster fieldMaster = new FieldMaster()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .isRequired(DEFAULT_IS_REQUIRED)
            .custom(DEFAULT_CUSTOM);
        return fieldMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldMaster createUpdatedEntity() {
        FieldMaster fieldMaster = new FieldMaster()
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .isRequired(UPDATED_IS_REQUIRED)
            .custom(UPDATED_CUSTOM);
        return fieldMaster;
    }

    @BeforeEach
    public void initTest() {
        fieldMasterRepository.deleteAll();
        fieldMaster = createEntity();
    }

    @Test
    public void createFieldMaster() throws Exception {
        int databaseSizeBeforeCreate = fieldMasterRepository.findAll().size();
        // Create the FieldMaster
        FieldMasterDTO fieldMasterDTO = fieldMasterMapper.toDto(fieldMaster);
        restFieldMasterMockMvc.perform(post("/api/field-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the FieldMaster in the database
        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeCreate + 1);
        FieldMaster testFieldMaster = fieldMasterList.get(fieldMasterList.size() - 1);
        assertThat(testFieldMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFieldMaster.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testFieldMaster.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFieldMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFieldMaster.isIsRequired()).isEqualTo(DEFAULT_IS_REQUIRED);
        assertThat(testFieldMaster.getCustom()).isEqualTo(DEFAULT_CUSTOM);
    }

    @Test
    public void createFieldMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldMasterRepository.findAll().size();

        // Create the FieldMaster with an existing ID
        fieldMaster.setId("existing_id");
        FieldMasterDTO fieldMasterDTO = fieldMasterMapper.toDto(fieldMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldMasterMockMvc.perform(post("/api/field-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FieldMaster in the database
        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldMasterRepository.findAll().size();
        // set the field null
        fieldMaster.setName(null);

        // Create the FieldMaster, which fails.
        FieldMasterDTO fieldMasterDTO = fieldMasterMapper.toDto(fieldMaster);


        restFieldMasterMockMvc.perform(post("/api/field-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldMasterDTO)))
            .andExpect(status().isBadRequest());

        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllFieldMasters() throws Exception {
        // Initialize the database
        fieldMasterRepository.save(fieldMaster);

        // Get all the fieldMasterList
        restFieldMasterMockMvc.perform(get("/api/field-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldMaster.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isRequired").value(hasItem(DEFAULT_IS_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)));
    }
    
    @Test
    public void getFieldMaster() throws Exception {
        // Initialize the database
        fieldMasterRepository.save(fieldMaster);

        // Get the fieldMaster
        restFieldMasterMockMvc.perform(get("/api/field-masters/{id}", fieldMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldMaster.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isRequired").value(DEFAULT_IS_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM));
    }
    @Test
    public void getNonExistingFieldMaster() throws Exception {
        // Get the fieldMaster
        restFieldMasterMockMvc.perform(get("/api/field-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFieldMaster() throws Exception {
        // Initialize the database
        fieldMasterRepository.save(fieldMaster);

        int databaseSizeBeforeUpdate = fieldMasterRepository.findAll().size();

        // Update the fieldMaster
        FieldMaster updatedFieldMaster = fieldMasterRepository.findById(fieldMaster.getId()).get();
        updatedFieldMaster
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .isRequired(UPDATED_IS_REQUIRED)
            .custom(UPDATED_CUSTOM);
        FieldMasterDTO fieldMasterDTO = fieldMasterMapper.toDto(updatedFieldMaster);

        restFieldMasterMockMvc.perform(put("/api/field-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldMasterDTO)))
            .andExpect(status().isOk());

        // Validate the FieldMaster in the database
        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeUpdate);
        FieldMaster testFieldMaster = fieldMasterList.get(fieldMasterList.size() - 1);
        assertThat(testFieldMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFieldMaster.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testFieldMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFieldMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFieldMaster.isIsRequired()).isEqualTo(UPDATED_IS_REQUIRED);
        assertThat(testFieldMaster.getCustom()).isEqualTo(UPDATED_CUSTOM);
    }

    @Test
    public void updateNonExistingFieldMaster() throws Exception {
        int databaseSizeBeforeUpdate = fieldMasterRepository.findAll().size();

        // Create the FieldMaster
        FieldMasterDTO fieldMasterDTO = fieldMasterMapper.toDto(fieldMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldMasterMockMvc.perform(put("/api/field-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FieldMaster in the database
        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFieldMaster() throws Exception {
        // Initialize the database
        fieldMasterRepository.save(fieldMaster);

        int databaseSizeBeforeDelete = fieldMasterRepository.findAll().size();

        // Delete the fieldMaster
        restFieldMasterMockMvc.perform(delete("/api/field-masters/{id}", fieldMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FieldMaster> fieldMasterList = fieldMasterRepository.findAll();
        assertThat(fieldMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
