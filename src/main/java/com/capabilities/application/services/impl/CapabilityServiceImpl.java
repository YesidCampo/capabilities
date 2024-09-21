package com.capabilities.application.services.impl;

import com.capabilities.application.services.CapabilityForTechnologyService;
import com.capabilities.application.services.CapabilityService;
import com.capabilities.domain.exception.InvalidCapabilityException;
import com.capabilities.domain.models.Capability;
import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.ports.in.CreateCapabilityUseCase;
import com.capabilities.domain.ports.in.RetrieveCapabilityUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapabilityServiceImpl implements CapabilityService {

    private final CreateCapabilityUseCase createCapabilityUseCase;
    private final RetrieveCapabilityUseCase retrieveCapabilityUseCase;
    private final CapabilityForTechnologyService capabilityForTechnologyService;

    public CapabilityServiceImpl(CreateCapabilityUseCase createCapabilityUseCase,
            RetrieveCapabilityUseCase retrieveCapabilityUseCase,
            CapabilityForTechnologyService capabilityForTechnologyService) {
        this.createCapabilityUseCase = createCapabilityUseCase;
        this.retrieveCapabilityUseCase = retrieveCapabilityUseCase;
        this.capabilityForTechnologyService = capabilityForTechnologyService;
    }

    @Override
    public Mono<Capability> createCapability(Capability capability) {
        if (capability.getTechnologies().size() < 3) {
            return Mono.error(new InvalidCapabilityException("Debe tener mas de 3 relaciones de tegnologia"));
        }
        if (capability.getTechnologies().size() > 20) {
            return Mono.error(new InvalidCapabilityException("Debe tener menos de 20 relaciones de tecnologia"));
        }
        return this.createCapabilityUseCase.createCapability(capability)
                .flatMap(createdCapability -> Flux
                        .fromStream(capability.getTechnologies().stream().distinct())
                        .flatMap(technology -> this.capabilityForTechnologyService
                                .save(new CapabilityForTechnology(createdCapability.getId(),
                                        technology.getId()))
                                .switchIfEmpty(Mono
                                        .error(new Exception("CapabilityForTechnology not created")))
                                .then(Mono.just(technology)))
                        .collectList().flatMap(list -> {
                            createdCapability.setTechnologies(list);
                            return Mono.just(createdCapability);
                        }))
                .switchIfEmpty(Mono.error(new Exception("Capability not created")));
    }

    @Override
    public Mono<Capability> getCapabilityByName(String name) {
        return this.retrieveCapabilityUseCase.getCapabilityByName(name);
    }

}
