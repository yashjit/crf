package com.nttdata.crf.web.rest;

import com.nttdata.crf.CrfApp;
import com.nttdata.crf.domain.Application;
import com.nttdata.crf.repository.ApplicationRepository;
import com.nttdata.crf.service.ApplicationService;
import com.nttdata.crf.service.dto.ApplicationDTO;
import com.nttdata.crf.service.mapper.ApplicationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ApplicationResource} REST controller.
 */
@SpringBootTest(classes = CrfApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApplicationResourceIT {

    private static final String DEFAULT_APP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_APP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APP_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM = "BBBBBBBBBB";

    @Autowired
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationRepository applicationRepositoryMock;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Mock
    private ApplicationService applicationServiceMock;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private MockMvc restApplicationMockMvc;

    private Application application;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Application createEntity() {
        Application application = new Application()
            .appName(DEFAULT_APP_NAME)
            .appType(DEFAULT_APP_TYPE)
            .custom(DEFAULT_CUSTOM);
        return application;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Application createUpdatedEntity() {
        Application application = new Application()
            .appName(UPDATED_APP_NAME)
            .appType(UPDATED_APP_TYPE)
            .custom(UPDATED_CUSTOM);
        return application;
    }

    @BeforeEach
    public void initTest() {
        applicationRepository.deleteAll();
        application = createEntity();
    }

    @Test
    public void createApplication() throws Exception {
        int databaseSizeBeforeCreate = applicationRepository.findAll().size();
        // Create the Application
        ApplicationDTO applicationDTO = applicationMapper.toDto(application);
        restApplicationMockMvc.perform(post("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isCreated());

        // Validate the Application in the database
        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeCreate + 1);
        Application testApplication = applicationList.get(applicationList.size() - 1);
        assertThat(testApplication.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testApplication.getAppType()).isEqualTo(DEFAULT_APP_TYPE);
        assertThat(testApplication.getCustom()).isEqualTo(DEFAULT_CUSTOM);
    }

    @Test
    public void createApplicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = applicationRepository.findAll().size();

        // Create the Application with an existing ID
        application.setId("existing_id");
        ApplicationDTO applicationDTO = applicationMapper.toDto(application);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApplicationMockMvc.perform(post("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Application in the database
        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkAppNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationRepository.findAll().size();
        // set the field null
        application.setAppName(null);

        // Create the Application, which fails.
        ApplicationDTO applicationDTO = applicationMapper.toDto(application);


        restApplicationMockMvc.perform(post("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isBadRequest());

        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAppTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = applicationRepository.findAll().size();
        // set the field null
        application.setAppType(null);

        // Create the Application, which fails.
        ApplicationDTO applicationDTO = applicationMapper.toDto(application);


        restApplicationMockMvc.perform(post("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isBadRequest());

        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllApplications() throws Exception {
        // Initialize the database
        applicationRepository.save(application);

        // Get all the applicationList
        restApplicationMockMvc.perform(get("/api/applications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(application.getId())))
            .andExpect(jsonPath("$.[*].appName").value(hasItem(DEFAULT_APP_NAME)))
            .andExpect(jsonPath("$.[*].appType").value(hasItem(DEFAULT_APP_TYPE)))
            .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllApplicationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(applicationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restApplicationMockMvc.perform(get("/api/applications?eagerload=true"))
            .andExpect(status().isOk());

        verify(applicationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllApplicationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(applicationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restApplicationMockMvc.perform(get("/api/applications?eagerload=true"))
            .andExpect(status().isOk());

        verify(applicationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getApplication() throws Exception {
        // Initialize the database
        applicationRepository.save(application);

        // Get the application
        restApplicationMockMvc.perform(get("/api/applications/{id}", application.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(application.getId()))
            .andExpect(jsonPath("$.appName").value(DEFAULT_APP_NAME))
            .andExpect(jsonPath("$.appType").value(DEFAULT_APP_TYPE))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM));
    }
    @Test
    public void getNonExistingApplication() throws Exception {
        // Get the application
        restApplicationMockMvc.perform(get("/api/applications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateApplication() throws Exception {
        // Initialize the database
        applicationRepository.save(application);

        int databaseSizeBeforeUpdate = applicationRepository.findAll().size();

        // Update the application
        Application updatedApplication = applicationRepository.findById(application.getId()).get();
        updatedApplication
            .appName(UPDATED_APP_NAME)
            .appType(UPDATED_APP_TYPE)
            .custom(UPDATED_CUSTOM);
        ApplicationDTO applicationDTO = applicationMapper.toDto(updatedApplication);

        restApplicationMockMvc.perform(put("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isOk());

        // Validate the Application in the database
        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeUpdate);
        Application testApplication = applicationList.get(applicationList.size() - 1);
        assertThat(testApplication.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testApplication.getAppType()).isEqualTo(UPDATED_APP_TYPE);
        assertThat(testApplication.getCustom()).isEqualTo(UPDATED_CUSTOM);
    }

    @Test
    public void updateNonExistingApplication() throws Exception {
        int databaseSizeBeforeUpdate = applicationRepository.findAll().size();

        // Create the Application
        ApplicationDTO applicationDTO = applicationMapper.toDto(application);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApplicationMockMvc.perform(put("/api/applications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(applicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Application in the database
        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteApplication() throws Exception {
        // Initialize the database
        applicationRepository.save(application);

        int databaseSizeBeforeDelete = applicationRepository.findAll().size();

        // Delete the application
        restApplicationMockMvc.perform(delete("/api/applications/{id}", application.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Application> applicationList = applicationRepository.findAll();
        assertThat(applicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
