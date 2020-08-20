package com.nttdata.crf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.ConfigMaster} entity.
 */
public class ConfigMasterDTO implements Serializable {
    
    private String id;

    @NotNull
    private String name;

    private String value;

    private String type;

    private String description;

    private Boolean isRequired;

    private String custom;


    private String organizationId;

    private String organizationOrgName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationOrgName() {
        return organizationOrgName;
    }

    public void setOrganizationOrgName(String organizationOrgName) {
        this.organizationOrgName = organizationOrgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigMasterDTO)) {
            return false;
        }

        return id != null && id.equals(((ConfigMasterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConfigMasterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", isRequired='" + isIsRequired() + "'" +
            ", custom='" + getCustom() + "'" +
            ", organizationId='" + getOrganizationId() + "'" +
            ", organizationOrgName='" + getOrganizationOrgName() + "'" +
            "}";
    }
}
