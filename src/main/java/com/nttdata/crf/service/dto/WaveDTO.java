package com.nttdata.crf.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Wave} entity.
 */
public class WaveDTO implements Serializable {
    
    private String id;

    @NotNull
    private String waveName;

    private String fileName;

    private Instant startDate;

    private Instant endDate;

    private String status;

    private String custom;

    private String createdBy;

    private Instant createDate;

    private Instant modifyDate;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")

    private String projectId;

    private String projectProjectName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaveName() {
        return waveName;
    }

    public void setWaveName(String waveName) {
        this.waveName = waveName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectProjectName() {
        return projectProjectName;
    }

    public void setProjectProjectName(String projectProjectName) {
        this.projectProjectName = projectProjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WaveDTO)) {
            return false;
        }

        return id != null && id.equals(((WaveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WaveDTO{" +
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
            ", projectId='" + getProjectId() + "'" +
            ", projectProjectName='" + getProjectProjectName() + "'" +
            "}";
    }
}
