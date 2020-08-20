package com.nttdata.crf.domain;

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
 * The Organization entity.
 */
@Document(collection = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * The firstname attribute.
     */
    @NotNull
    @Size(max = 50)
    @Field("org_name")
    private String orgName;

    @NotNull
    @Size(max = 20)
    @Field("first_name")
    private String firstName;

    @Size(max = 20)
    @Field("last_name")
    private String lastName;

    @NotNull
    @Field("email")
    private String email;

    @NotNull
    @Field("phone_number")
    private String phoneNumber;

    @Field("create_date")
    private Instant createDate;

    @DBRef
    @Field("location")
    private Location location;

    /**
     * A relationship
     */
    @DBRef
    @Field("project")
    private Set<Project> projects = new HashSet<>();

    /**
     * A relationship
     */
    @DBRef
    @Field("team")
    private Set<Team> teams = new HashSet<>();

    /**
     * A relationship
     */
    @DBRef
    @Field("configMaster")
    private Set<ConfigMaster> configMasters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public Organization orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Organization firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Organization lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Organization email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Organization phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Organization createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Location getLocation() {
        return location;
    }

    public Organization location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Organization projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Organization addProject(Project project) {
        this.projects.add(project);
        project.setOrganization(this);
        return this;
    }

    public Organization removeProject(Project project) {
        this.projects.remove(project);
        project.setOrganization(null);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Organization teams(Set<Team> teams) {
        this.teams = teams;
        return this;
    }

    public Organization addTeam(Team team) {
        this.teams.add(team);
        team.setOrganization(this);
        return this;
    }

    public Organization removeTeam(Team team) {
        this.teams.remove(team);
        team.setOrganization(null);
        return this;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<ConfigMaster> getConfigMasters() {
        return configMasters;
    }

    public Organization configMasters(Set<ConfigMaster> configMasters) {
        this.configMasters = configMasters;
        return this;
    }

    public Organization addConfigMaster(ConfigMaster configMaster) {
        this.configMasters.add(configMaster);
        configMaster.setOrganization(this);
        return this;
    }

    public Organization removeConfigMaster(ConfigMaster configMaster) {
        this.configMasters.remove(configMaster);
        configMaster.setOrganization(null);
        return this;
    }

    public void setConfigMasters(Set<ConfigMaster> configMasters) {
        this.configMasters = configMasters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", orgName='" + getOrgName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
