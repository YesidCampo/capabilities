package com.capabilities.application.usercases;


import com.capabilities.domain.models.Capability;
import com.capabilities.domain.ports.in.RetrieveCapabilityUseCase;
import com.capabilities.domain.ports.out.CapabilityRepositoryPort;

import reactor.core.publisher.Mono;

public class RetrieveCapabilityUseCaseImpl implements RetrieveCapabilityUseCase {

    private final CapabilityRepositoryPort capabilityRepositoryPort;

    public RetrieveCapabilityUseCaseImpl(CapabilityRepositoryPort capabilityRepositoryPort) {
        this.capabilityRepositoryPort = capabilityRepositoryPort;
    }

    @Override
    public Mono<Capability> getCapabilityByName(String name) {
        return this.capabilityRepositoryPort.findByName(name);
    }

}
