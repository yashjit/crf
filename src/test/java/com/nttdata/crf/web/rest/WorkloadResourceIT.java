package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.Workload;
import com.nttdata.crf.repository.WorkloadRepository;
import com.nttdata.crf.service.WorkloadService;
import com.nttdata.crf.service.dto.WorkloadDTO;
import com.nttdata.crf.service.mapper.WorkloadMapper;

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
 * Integration tests for the {@link WorkloadResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WorkloadResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOVEGROUP = 1;
    private static final Integer UPDATED_MOVEGROUP = 2;

    private static final String DEFAULT_SERVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVER_TIER = "AAAAAAAAAA";
    private static final String UPDATED_SERVER_TIER = "BBBBBBBBBB";

    private static final String DEFAULT_OS = "AAAAAAAAAA";
    private static final String UPDATED_OS = "BBBBBBBBBB";

    private static final String DEFAULT_CLOUD_INFO = "AAAAAAAAAA";
    private static final String UPDATED_CLOUD_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private WorkloadRepository workloadRepository;

    @Autowired
    private WorkloadMapper workloadMapper;

    @Autowired
    private WorkloadService workloadService;

    @Autowired
    private MockMvc restWorkloadMockMvc;

    private Workload workload;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workload createEntity() {
        Workload workload = new Workload()
            .title(DEFAULT_TITLE)
            .type(DEFAULT_TYPE)
            .movegroup(DEFAULT_MOVEGROUP)
            .serverName(DEFAULT_SERVER_NAME)
            .serverTier(DEFAULT_SERVER_TIER)
            .os(DEFAULT_OS)
            .cloudInfo(DEFAULT_CLOUD_INFO)
            .description(DEFAULT_DESCRIPTION)
            .custom(DEFAULT_CUSTOM)
            .status(DEFAULT_STATUS)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE)
            .createdBy(DEFAULT_CREATED_BY);
        return workload;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workload createUpdatedEntity() {
        Workload workload = new Workload()
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .movegroup(UPDATED_MOVEGROUP)
            .serverName(UPDATED_SERVER_NAME)
            .serverTier(UPDATED_SERVER_TIER)
            .os(UPDATED_OS)
            .cloudInfo(UPDATED_CLOUD_INFO)
            .description(UPDATED_DESCRIPTION)
            .custom(UPDATED_CUSTOM)
            .status(UPDATED_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE)
            .createdBy(UPDATED_CREATED_BY);
        return workload;
    }

    @BeforeEach
    public void initTest() {
        workloadRepository.deleteAll();
        workload = createEntity();
    }

    @Test
    public void createWorkload() throws Exception {
        int databaseSizeBeforeCreate = workloadRepository.findAll().size();
        // Create the Workload
        WorkloadDTO workloadDTO = workloadMapper.toDto(workload);
        restWorkloadMockMvc.perform(post("/api/workloads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workloadDTO)))
            .andExpect(status().isCreated());

        // Validate the Workload in the database
        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeCreate + 1);
        Workload testWorkload = workloadList.get(workloadList.size() - 1);
        assertThat(testWorkload.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testWorkload.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testWorkload.getMovegroup()).isEqualTo(DEFAULT_MOVEGROUP);
        assertThat(testWorkload.getServerName()).isEqualTo(DEFAULT_SERVER_NAME);
        assertThat(testWorkload.getServerTier()).isEqualTo(DEFAULT_SERVER_TIER);
        assertThat(testWorkload.getOs()).isEqualTo(DEFAULT_OS);
        assertThat(testWorkload.getCloudInfo()).isEqualTo(DEFAULT_CLOUD_INFO);
        assertThat(testWorkload.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testWorkload.getCustom()).isEqualTo(DEFAULT_CUSTOM);
        assertThat(testWorkload.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testWorkload.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testWorkload.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
        assertThat(testWorkload.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    public void createWorkloadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workloadRepository.findAll().size();

        // Create the Workload with an existing ID
        workload.setId("existing_id");
        WorkloadDTO workloadDTO = workloadMapper.toDto(workload);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkloadMockMvc.perform(post("/api/workloads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workloadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Workload in the database
        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = workloadRepository.findAll().size();
        // set the field null
        workload.setTitle(null);

        // Create the Workload, which fails.
        WorkloadDTO workloadDTO = workloadMapper.toDto(workload);


        restWorkloadMockMvc.perform(post("/api/workloads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workloadDTO)))
            .andExpect(status().isBadRequest());

        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllWorkloads() throws Exception {
        // Initialize the database
        workloadRepository.save(workload);

        // Get all the workloadList
        restWorkloadMockMvc.perform(get("/api/workloads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workload.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].movegroup").value(hasItem(DEFAULT_MOVEGROUP)))
            .andExpect(jsonPath("$.[*].serverName").value(hasItem(DEFAULT_SERVER_NAME)))
            .andExpect(jsonPath("$.[*].serverTier").value(hasItem(DEFAULT_SERVER_TIER)))
            .andExpect(jsonPath("$.[*].os").value(hasItem(DEFAULT_OS)))
            .andExpect(jsonPath("$.[*].cloudInfo").value(hasItem(DEFAULT_CLOUD_INFO)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    public void getWorkload() throws Exception {
        // Initialize the database
        workloadRepository.save(workload);

        // Get the workload
        restWorkloadMockMvc.perform(get("/api/workloads/{id}", workload.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workload.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.movegroup").value(DEFAULT_MOVEGROUP))
            .andExpect(jsonPath("$.serverName").value(DEFAULT_SERVER_NAME))
            .andExpect(jsonPath("$.serverTier").value(DEFAULT_SERVER_TIER))
            .andExpect(jsonPath("$.os").value(DEFAULT_OS))
            .andExpect(jsonPath("$.cloudInfo").value(DEFAULT_CLOUD_INFO))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    public void getNonExistingWorkload() throws Exception {
        // Get the workload
        restWorkloadMockMvc.perform(get("/api/workloads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWorkload() throws Exception {
        // Initialize the database
        workloadRepository.save(workload);

        int databaseSizeBeforeUpdate = workloadRepository.findAll().size();

        // Update the workload
        Workload updatedWorkload = workloadRepository.findById(workload.getId()).get();
        updatedWorkload
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .movegroup(UPDATED_MOVEGROUP)
            .serverName(UPDATED_SERVER_NAME)
            .serverTier(UPDATED_SERVER_TIER)
            .os(UPDATED_OS)
            .cloudInfo(UPDATED_CLOUD_INFO)
            .description(UPDATED_DESCRIPTION)
            .custom(UPDATED_CUSTOM)
            .status(UPDATED_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE)
            .createdBy(UPDATED_CREATED_BY);
        WorkloadDTO workloadDTO = workloadMapper.toDto(updatedWorkload);

        restWorkloadMockMvc.perform(put("/api/workloads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workloadDTO)))
            .andExpect(status().isOk());

        // Validate the Workload in the database
        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeUpdate);
        Workload testWorkload = workloadList.get(workloadList.size() - 1);
        assertThat(testWorkload.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testWorkload.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testWorkload.getMovegroup()).isEqualTo(UPDATED_MOVEGROUP);
        assertThat(testWorkload.getServerName()).isEqualTo(UPDATED_SERVER_NAME);
        assertThat(testWorkload.getServerTier()).isEqualTo(UPDATED_SERVER_TIER);
        assertThat(testWorkload.getOs()).isEqualTo(UPDATED_OS);
        assertThat(testWorkload.getCloudInfo()).isEqualTo(UPDATED_CLOUD_INFO);
        assertThat(testWorkload.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testWorkload.getCustom()).isEqualTo(UPDATED_CUSTOM);
        assertThat(testWorkload.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testWorkload.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testWorkload.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
        assertThat(testWorkload.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    public void updateNonExistingWorkload() throws Exception {
        int databaseSizeBeforeUpdate = workloadRepository.findAll().size();

        // Create the Workload
        WorkloadDTO workloadDTO = workloadMapper.toDto(workload);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkloadMockMvc.perform(put("/api/workloads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workloadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Workload in the database
        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteWorkload() throws Exception {
        // Initialize the database
        workloadRepository.save(workload);

        int databaseSizeBeforeDelete = workloadRepository.findAll().size();

        // Delete the workload
        restWorkloadMockMvc.perform(delete("/api/workloads/{id}", workload.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Workload> workloadList = workloadRepository.findAll();
        assertThat(workloadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
