package com.nttdata.crf.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nttdata.crf.domain.Organization} entity.
 */
@ApiModel(description = "The Organization entity.")
public class OrganizationDTO implements Serializable {
    
    private String id;

    /**
     * The firstname attribute.
     */
    @NotNull
    @Size(max = 50)
    @ApiModelProperty(value = "The firstname attribute.", required = true)
    private String orgName;

    @NotNull
    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    private Instant createDate;


    private String locationId;

    private String locationTitle;
    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganizationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", orgName='" + getOrgName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", locationId='" + getLocationId() + "'" +
            ", locationTitle='" + getLocationTitle() + "'" +
            "}";
    }
}
