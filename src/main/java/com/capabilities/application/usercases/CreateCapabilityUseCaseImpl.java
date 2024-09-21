package com.capabilities.application.usercases;


import com.capabilities.domain.models.Capability;
import com.capabilities.domain.ports.in.CreateCapabilityUseCase;
import com.capabilities.domain.ports.out.CapabilityRepositoryPort;

import reactor.core.publisher.Mono;

public class CreateCapabilityUseCaseImpl implements CreateCapabilityUseCase {

    private final CapabilityRepositoryPort capabilityRepositoryPort;

    public CreateCapabilityUseCaseImpl(CapabilityRepositoryPort capabilityRepositoryPort) {
        this.capabilityRepositoryPort = capabilityRepositoryPort;
    }

    @Override
    public Mono<Capability> createCapability(Capability capability) {
        return this.capabilityRepositoryPort.save(capability);
    }

}
