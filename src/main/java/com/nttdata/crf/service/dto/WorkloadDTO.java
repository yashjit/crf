package com.nttdata.crf.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Workload} entity.
 */
@ApiModel(description = "Task entity.\n@author The JHipster team.")
public class WorkloadDTO implements Serializable {
    
    private String id;

    @NotNull
    private String title;

    private String type;

    private Integer movegroup;

    private String serverName;

    private String serverTier;

    private String os;

    private String cloudInfo;

    private String description;

    private String custom;

    private String status;

    private Instant createDate;

    private Instant modifyDate;

    private String createdBy;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMovegroup() {
        return movegroup;
    }

    public void setMovegroup(Integer movegroup) {
        this.movegroup = movegroup;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerTier() {
        return serverTier;
    }

    public void setServerTier(String serverTier) {
        this.serverTier = serverTier;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCloudInfo() {
        return cloudInfo;
    }

    public void setCloudInfo(String cloudInfo) {
        this.cloudInfo = cloudInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkloadDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkloadDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkloadDTO{" +
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
