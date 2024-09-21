package com.capabilities.infrastruture.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "capability_technology")
public class CapabilityForTechnologyEntity {

    @Id
    private Long id;
    private Long capabilityId;
    private Long technologyId;

    public CapabilityForTechnologyEntity() {
        super();
    }

    public CapabilityForTechnologyEntity(Long capabilityId, Long technologyId) {
        this.capabilityId = capabilityId;
        this.technologyId = technologyId;
    }

    public CapabilityForTechnologyEntity(Long id, Long capabilityId, Long technologyId) {
        this.id = id;
        this.capabilityId = capabilityId;
        this.technologyId = technologyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(Long capabilityId) {
        this.capabilityId = capabilityId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

}
