package com.capabilities.infrastruture.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import com.capabilities.domain.models.Technology;

import java.util.List;

@Table(name = "capabilities")
public class CapabilityEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    @Transient
    private List<Technology> technologies;

    public CapabilityEntity() {
        super();
    }

    public CapabilityEntity(Long id, String name, String description, List<Technology> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologies = technologies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

}
