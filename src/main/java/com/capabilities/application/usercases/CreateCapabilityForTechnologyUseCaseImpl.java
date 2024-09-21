package com.capabilities.application.usercases;

import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.ports.in.CreateCapabilityForTechnologyUseCase;
import com.capabilities.domain.ports.out.CapabilityForTechnologyRepositoryPort;

import reactor.core.publisher.Mono;

public class CreateCapabilityForTechnologyUseCaseImpl implements CreateCapabilityForTechnologyUseCase {

    private final CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort;

    public CreateCapabilityForTechnologyUseCaseImpl(
            CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort) {
        this.capabilityForTechnologyRepositoryPort = capabilityForTechnologyRepositoryPort;
    }

    @Override
    public Mono<CapabilityForTechnology> save(CapabilityForTechnology capabilityForTechnology) {
        return this.capabilityForTechnologyRepositoryPort.save(capabilityForTechnology);
    }

}
