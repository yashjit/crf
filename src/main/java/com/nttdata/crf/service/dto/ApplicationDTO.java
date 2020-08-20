package com.nttdata.crf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Application} entity.
 */
public class ApplicationDTO implements Serializable {
    
    private String id;

    @NotNull
    private String appName;

    @NotNull
    private String appType;

    private String custom;

    private Set<WorkloadDTO> workloads = new HashSet<>();

    private String waveId;

    private String waveWaveName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public Set<WorkloadDTO> getWorkloads() {
        return workloads;
    }

    public void setWorkloads(Set<WorkloadDTO> workloads) {
        this.workloads = workloads;
    }

    public String getWaveId() {
        return waveId;
    }

    public void setWaveId(String waveId) {
        this.waveId = waveId;
    }

    public String getWaveWaveName() {
        return waveWaveName;
    }

    public void setWaveWaveName(String waveWaveName) {
        this.waveWaveName = waveWaveName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationDTO)) {
            return false;
        }

        return id != null && id.equals(((ApplicationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationDTO{" +
            "id=" + getId() +
            ", appName='" + getAppName() + "'" +
            ", appType='" + getAppType() + "'" +
            ", custom='" + getCustom() + "'" +
            ", workloads='" + getWorkloads() + "'" +
            ", waveId='" + getWaveId() + "'" +
            ", waveWaveName='" + getWaveWaveName() + "'" +
            "}";
    }
}
