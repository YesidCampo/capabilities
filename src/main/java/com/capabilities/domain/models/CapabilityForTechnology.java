package com.capabilities.domain.models;

public class CapabilityForTechnology {

    private Long id;
    private Long capabilityId;
    private Long technologyId;

    public CapabilityForTechnology() {
    }

    public CapabilityForTechnology(Long capabilityId, Long technologyId) {
        this.capabilityId = capabilityId;
        this.technologyId = technologyId;
    }

    public CapabilityForTechnology(Long id, Long capabilityId, Long technologyId) {
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
