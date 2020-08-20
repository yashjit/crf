package com.nttdata.crf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Wave.
 */
@Document(collection = "wave")
public class Wave implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("wave_name")
    private String waveName;

    @Field("file_name")
    private String fileName;

    @Field("start_date")
    private Instant startDate;

    @Field("end_date")
    private Instant endDate;

    @Field("status")
    private String status;

    @Field("custom")
    private String custom;

    @Field("created_by")
    private String createdBy;

    @Field("create_date")
    private Instant createDate;

    @Field("modify_date")
    private Instant modifyDate;

    /**
     * A relationship
     */
    @DBRef
    @Field("application")
    private Set<Application> applications = new HashSet<>();

    @DBRef
    @Field("project")
    @JsonIgnoreProperties(value = "waves", allowSetters = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaveName() {
        return waveName;
    }

    public Wave waveName(String waveName) {
        this.waveName = waveName;
        return this;
    }

    public void setWaveName(String waveName) {
        this.waveName = waveName;
    }

    public String getFileName() {
        return fileName;
    }

    public Wave fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Wave startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Wave endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public Wave status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustom() {
        return custom;
    }

    public Wave custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Wave createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Wave createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Wave modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public Wave applications(Set<Application> applications) {
        this.applications = applications;
        return this;
    }

    public Wave addApplication(Application application) {
        this.applications.add(application);
        application.setWave(this);
        return this;
    }

    public Wave removeApplication(Application application) {
        this.applications.remove(application);
        application.setWave(null);
        return this;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Project getProject() {
        return project;
    }

    public Wave project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wave)) {
            return false;
        }
        return id != null && id.equals(((Wave) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wave{" +
            "id=" + getId() +
            ", waveName='" + getWaveName() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", custom='" + getCustom() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
