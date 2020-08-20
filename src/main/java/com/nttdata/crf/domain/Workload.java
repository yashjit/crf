package com.nttdata.crf.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Task entity.\n@author The JHipster team.
 */
@Document(collection = "workload")
public class Workload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("title")
    private String title;

    @Field("type")
    private String type;

    @Field("movegroup")
    private Integer movegroup;

    @Field("server_name")
    private String serverName;

    @Field("server_tier")
    private String serverTier;

    @Field("os")
    private String os;

    @Field("cloud_info")
    private String cloudInfo;

    @Field("description")
    private String description;

    @Field("custom")
    private String custom;

    @Field("status")
    private String status;

    @Field("create_date")
    private Instant createDate;

    @Field("modify_date")
    private Instant modifyDate;

    @Field("created_by")
    private String createdBy;

    /**
     * A relationship
     */
    @DBRef
    @Field("blueprint")
    private Set<Blueprint> blueprints = new HashSet<>();

    @DBRef
    @Field("waves")
    @JsonIgnore
    private Set<Application> waves = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Workload title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public Workload type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMovegroup() {
        return movegroup;
    }

    public Workload movegroup(Integer movegroup) {
        this.movegroup = movegroup;
        return this;
    }

    public void setMovegroup(Integer movegroup) {
        this.movegroup = movegroup;
    }

    public String getServerName() {
        return serverName;
    }

    public Workload serverName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerTier() {
        return serverTier;
    }

    public Workload serverTier(String serverTier) {
        this.serverTier = serverTier;
        return this;
    }

    public void setServerTier(String serverTier) {
        this.serverTier = serverTier;
    }

    public String getOs() {
        return os;
    }

    public Workload os(String os) {
        this.os = os;
        return this;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCloudInfo() {
        return cloudInfo;
    }

    public Workload cloudInfo(String cloudInfo) {
        this.cloudInfo = cloudInfo;
        return this;
    }

    public void setCloudInfo(String cloudInfo) {
        this.cloudInfo = cloudInfo;
    }

    public String getDescription() {
        return description;
    }

    public Workload description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustom() {
        return custom;
    }

    public Workload custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getStatus() {
        return status;
    }

    public Workload status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Workload createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public Workload modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Workload createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Blueprint> getBlueprints() {
        return blueprints;
    }

    public Workload blueprints(Set<Blueprint> blueprints) {
        this.blueprints = blueprints;
        return this;
    }

    public Workload addBlueprint(Blueprint blueprint) {
        this.blueprints.add(blueprint);
        blueprint.setWorkload(this);
        return this;
    }

    public Workload removeBlueprint(Blueprint blueprint) {
        this.blueprints.remove(blueprint);
        blueprint.setWorkload(null);
        return this;
    }

    public void setBlueprints(Set<Blueprint> blueprints) {
        this.blueprints = blueprints;
    }

    public Set<Application> getWaves() {
        return waves;
    }

    public Workload waves(Set<Application> applications) {
        this.waves = applications;
        return this;
    }

    public Workload addWave(Application application) {
        this.waves.add(application);
        application.getWorkloads().add(this);
        return this;
    }

    public Workload removeWave(Application application) {
        this.waves.remove(application);
        application.getWorkloads().remove(this);
        return this;
    }

    public void setWaves(Set<Application> applications) {
        this.waves = applications;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workload)) {
            return false;
        }
        return id != null && id.equals(((Workload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workload{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", type='" + getType() + "'" +
            ", movegroup=" + getMovegroup() +
            ", serverName='" + getServerName() + "'" +
            ", serverTier='" + getServerTier() + "'" +
            ", os='" + getOs() + "'" +
            ", cloudInfo='" + getCloudInfo() + "'" +
            ", description='" + getDescription() + "'" +
            ", custom='" + getCustom() + "'" +
            ", status='" + getStatus() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
