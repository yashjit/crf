package com.nttdata.crf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Blueprint.
 */
@Document(collection = "blueprint")
public class Blueprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("title")
    private String title;

    @Field("type")
    private String type;

    @Field("description")
    private String description;

    @Field("template")
    private String template;

    @Field("custom")
    private String custom;

    @DBRef
    @Field("workload")
    @JsonIgnoreProperties(value = "blueprints", allowSetters = true)
    private Workload workload;

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

    public Blueprint title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public Blueprint type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public Blueprint description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplate() {
        return template;
    }

    public Blueprint template(String template) {
        this.template = template;
        return this;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCustom() {
        return custom;
    }

    public Blueprint custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public Workload getWorkload() {
        return workload;
    }

    public Blueprint workload(Workload workload) {
        this.workload = workload;
        return this;
    }

    public void setWorkload(Workload workload) {
        this.workload = workload;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Blueprint)) {
            return false;
        }
        return id != null && id.equals(((Blueprint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Blueprint{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", template='" + getTemplate() + "'" +
            ", custom='" + getCustom() + "'" +
            "}";
    }
}
