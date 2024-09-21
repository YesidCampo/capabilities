package com.capabilities.infrastruture.utils;

import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.infrastruture.entities.CapabilityForTechnologyEntity;

public class CapabilityForTechnologyMapper {

    private CapabilityForTechnologyMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CapabilityForTechnologyEntity fromDomainModel(CapabilityForTechnology capabilityForTechnology) {
        return new CapabilityForTechnologyEntity(capabilityForTechnology.getId(),
                capabilityForTechnology.getCapabilityId(), capabilityForTechnology.getTechnologyId());
    }

    public static CapabilityForTechnology toDomainModel(CapabilityForTechnologyEntity capabilityForTechnologyEntity) {
        return new CapabilityForTechnology(capabilityForTechnologyEntity.getId(),
                capabilityForTechnologyEntity.getCapabilityId(),
                capabilityForTechnologyEntity.getTechnologyId());
    }

}
