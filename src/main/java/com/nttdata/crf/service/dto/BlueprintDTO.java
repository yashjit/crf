package com.nttdata.crf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Blueprint} entity.
 */
public class BlueprintDTO implements Serializable {
    
    private String id;

    @NotNull
    private String title;

    private String type;

    private String description;

    private String template;

    private String custom;


    private String workloadId;

    private String workloadTitle;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public String getWorkloadTitle() {
        return workloadTitle;
    }

    public void setWorkloadTitle(String workloadTitle) {
        this.workloadTitle = workloadTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlueprintDTO)) {
            return false;
        }

        return id != null && id.equals(((BlueprintDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BlueprintDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", template='" + getTemplate() + "'" +
            ", custom='" + getCustom() + "'" +
            ", workloadId='" + getWorkloadId() + "'" +
            ", workloadTitle='" + getWorkloadTitle() + "'" +
            "}";
    }
}
