package com.nttdata.crf.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Team} entity.
 */
public class TeamDTO implements Serializable {
    
    private String id;

    @NotNull
    @Size(max = 50)
    private String teamName;

    private Instant createDate;


    private String organizationId;

    private String organizationOrgName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
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
        if (!(o instanceof TeamDTO)) {
            return false;
        }

        return id != null && id.equals(((TeamDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamDTO{" +
            "id=" + getId() +
            ", teamName='" + getTeamName() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", organizationId='" + getOrganizationId() + "'" +
            ", organizationOrgName='" + getOrganizationOrgName() + "'" +
            "}";
    }
}
