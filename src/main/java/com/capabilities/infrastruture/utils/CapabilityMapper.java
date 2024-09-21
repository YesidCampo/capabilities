package com.capabilities.infrastruture.utils;

import com.capabilities.domain.models.Capability;
import com.capabilities.infrastruture.entities.CapabilityEntity;

public class CapabilityMapper {

    private CapabilityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CapabilityEntity fromDomainModel(Capability capability) {
        return new CapabilityEntity(capability.getId(), capability.getName(), capability.getDescription(),
                capability.getTechnologies());
    }

    public static Capability toDomainModel(CapabilityEntity capabilityEntity) {
        return new Capability(capabilityEntity.getId(), capabilityEntity.getName(), capabilityEntity.getDescription(),
                capabilityEntity.getTechnologies());
    }
}
