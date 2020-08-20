package com.nttdata.crf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Project.
 */
@Document(collection = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    @Field("project_name")
    private String projectName;

    @NotNull
    @Field("project_type")
    private String projectType;

    @NotNull
    @Field("target_cloud")
    private String targetCloud;

    @NotNull
    @Field("start_date")
    private LocalDate startDate;

    @NotNull
    @Field("end_date")
    private LocalDate endDate;

    @Field("custom")
    private String custom;

    @Field("status")
    private String status;

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
    @Field("wave")
    private Set<Wave> waves = new HashSet<>();

    @DBRef
    @Field("organization")
    @JsonIgnoreProperties(value = "projects", allowSetters = true)
    private Organization organization;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public Project projectType(String projectType) {
        this.projectType = projectType;
        return this;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTargetCloud() {
        return targetCloud;
    }

    public Project targetCloud(String targetCloud) {
        this.targetCloud = targetCloud;
        return this;
    }

    public void setTargetCloud(String targetCloud) {
        this.targetCloud = targetCloud;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Project startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Project endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCustom() {
        return custom;
    }

    public Project custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getStatus() {
        return status;
    }

    public Project status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Project createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Project createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Project modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<Wave> getWaves() {
        return waves;
    }

    public Project waves(Set<Wave> waves) {
        this.waves = waves;
        return this;
    }

    public Project addWave(Wave wave) {
        this.waves.add(wave);
        wave.setProject(this);
        return this;
    }

    public Project removeWave(Wave wave) {
        this.waves.remove(wave);
        wave.setProject(null);
        return this;
    }

    public void setWaves(Set<Wave> waves) {
        this.waves = waves;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Project organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectName='" + getProjectName() + "'" +
            ", projectType='" + getProjectType() + "'" +
            ", targetCloud='" + getTargetCloud() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", custom='" + getCustom() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
