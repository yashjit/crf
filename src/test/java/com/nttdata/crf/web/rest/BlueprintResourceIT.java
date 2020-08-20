package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.Blueprint;
import com.nttdata.crf.repository.BlueprintRepository;
import com.nttdata.crf.service.BlueprintService;
import com.nttdata.crf.service.dto.BlueprintDTO;
import com.nttdata.crf.service.mapper.BlueprintMapper;

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
 * Integration tests for the {@link BlueprintResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BlueprintResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM = "BBBBBBBBBB";

    @Autowired
    private BlueprintRepository blueprintRepository;

    @Autowired
    private BlueprintMapper blueprintMapper;

    @Autowired
    private BlueprintService blueprintService;

    @Autowired
    private MockMvc restBlueprintMockMvc;

    private Blueprint blueprint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blueprint createEntity() {
        Blueprint blueprint = new Blueprint()
            .title(DEFAULT_TITLE)
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .template(DEFAULT_TEMPLATE)
            .custom(DEFAULT_CUSTOM);
        return blueprint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blueprint createUpdatedEntity() {
        Blueprint blueprint = new Blueprint()
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .template(UPDATED_TEMPLATE)
            .custom(UPDATED_CUSTOM);
        return blueprint;
    }

    @BeforeEach
    public void initTest() {
        blueprintRepository.deleteAll();
        blueprint = createEntity();
    }

    @Test
    public void createBlueprint() throws Exception {
        int databaseSizeBeforeCreate = blueprintRepository.findAll().size();
        // Create the Blueprint
        BlueprintDTO blueprintDTO = blueprintMapper.toDto(blueprint);
        restBlueprintMockMvc.perform(post("/api/blueprints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blueprintDTO)))
            .andExpect(status().isCreated());

        // Validate the Blueprint in the database
        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeCreate + 1);
        Blueprint testBlueprint = blueprintList.get(blueprintList.size() - 1);
        assertThat(testBlueprint.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBlueprint.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBlueprint.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBlueprint.getTemplate()).isEqualTo(DEFAULT_TEMPLATE);
        assertThat(testBlueprint.getCustom()).isEqualTo(DEFAULT_CUSTOM);
    }

    @Test
    public void createBlueprintWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blueprintRepository.findAll().size();

        // Create the Blueprint with an existing ID
        blueprint.setId("existing_id");
        BlueprintDTO blueprintDTO = blueprintMapper.toDto(blueprint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlueprintMockMvc.perform(post("/api/blueprints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blueprintDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Blueprint in the database
        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = blueprintRepository.findAll().size();
        // set the field null
        blueprint.setTitle(null);

        // Create the Blueprint, which fails.
        BlueprintDTO blueprintDTO = blueprintMapper.toDto(blueprint);


        restBlueprintMockMvc.perform(post("/api/blueprints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blueprintDTO)))
            .andExpect(status().isBadRequest());

        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBlueprints() throws Exception {
        // Initialize the database
        blueprintRepository.save(blueprint);

        // Get all the blueprintList
        restBlueprintMockMvc.perform(get("/api/blueprints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blueprint.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].template").value(hasItem(DEFAULT_TEMPLATE)))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)));
    }
    
    @Test
    public void getBlueprint() throws Exception {
        // Initialize the database
        blueprintRepository.save(blueprint);

        // Get the blueprint
        restBlueprintMockMvc.perform(get("/api/blueprints/{id}", blueprint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(blueprint.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.template").value(DEFAULT_TEMPLATE))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM));
    }
    @Test
    public void getNonExistingBlueprint() throws Exception {
        // Get the blueprint
        restBlueprintMockMvc.perform(get("/api/blueprints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBlueprint() throws Exception {
        // Initialize the database
        blueprintRepository.save(blueprint);

        int databaseSizeBeforeUpdate = blueprintRepository.findAll().size();

        // Update the blueprint
        Blueprint updatedBlueprint = blueprintRepository.findById(blueprint.getId()).get();
        updatedBlueprint
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .template(UPDATED_TEMPLATE)
            .custom(UPDATED_CUSTOM);
        BlueprintDTO blueprintDTO = blueprintMapper.toDto(updatedBlueprint);

        restBlueprintMockMvc.perform(put("/api/blueprints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blueprintDTO)))
            .andExpect(status().isOk());

        // Validate the Blueprint in the database
        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeUpdate);
        Blueprint testBlueprint = blueprintList.get(blueprintList.size() - 1);
        assertThat(testBlueprint.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBlueprint.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBlueprint.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBlueprint.getTemplate()).isEqualTo(UPDATED_TEMPLATE);
        assertThat(testBlueprint.getCustom()).isEqualTo(UPDATED_CUSTOM);
    }

    @Test
    public void updateNonExistingBlueprint() throws Exception {
        int databaseSizeBeforeUpdate = blueprintRepository.findAll().size();

        // Create the Blueprint
        BlueprintDTO blueprintDTO = blueprintMapper.toDto(blueprint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlueprintMockMvc.perform(put("/api/blueprints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blueprintDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Blueprint in the database
        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBlueprint() throws Exception {
        // Initialize the database
        blueprintRepository.save(blueprint);

        int databaseSizeBeforeDelete = blueprintRepository.findAll().size();

        // Delete the blueprint
        restBlueprintMockMvc.perform(delete("/api/blueprints/{id}", blueprint.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Blueprint> blueprintList = blueprintRepository.findAll();
        assertThat(blueprintList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
