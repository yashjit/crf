package com.nttdata.crf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Application.
 */
@Document(collection = "application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("app_name")
    private String appName;

    @NotNull
    @Field("app_type")
    private String appType;

    @Field("custom")
    private String custom;

    @DBRef
    @Field("workloads")
    private Set<Workload> workloads = new HashSet<>();

    @DBRef
    @Field("wave")
    @JsonIgnoreProperties(value = "applications", allowSetters = true)
    private Wave wave;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public Application appName(String appName) {
        this.appName = appName;
        return this;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public Application appType(String appType) {
        this.appType = appType;
        return this;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getCustom() {
        return custom;
    }

    public Application custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public Set<Workload> getWorkloads() {
        return workloads;
    }

    public Application workloads(Set<Workload> workloads) {
        this.workloads = workloads;
        return this;
    }

    public Application addWorkload(Workload workload) {
        this.workloads.add(workload);
        workload.getWaves().add(this);
        return this;
    }

    public Application removeWorkload(Workload workload) {
        this.workloads.remove(workload);
        workload.getWaves().remove(this);
        return this;
    }

    public void setWorkloads(Set<Workload> workloads) {
        this.workloads = workloads;
    }

    public Wave getWave() {
        return wave;
    }

    public Application wave(Wave wave) {
        this.wave = wave;
        return this;
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        return id != null && id.equals(((Application) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Application{" +
            "id=" + getId() +
            ", appName='" + getAppName() + "'" +
            ", appType='" + getAppType() + "'" +
            ", custom='" + getCustom() + "'" +
            "}";
    }
}
