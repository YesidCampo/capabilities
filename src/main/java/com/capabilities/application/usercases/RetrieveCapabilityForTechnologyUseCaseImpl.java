package com.capabilities.application.usercases;

import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.ports.in.RetrieveCapabilityForTechnologyUseCase;
import com.capabilities.domain.ports.out.CapabilityForTechnologyRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RetrieveCapabilityForTechnologyUseCaseImpl implements RetrieveCapabilityForTechnologyUseCase {

    private final CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort;

    public RetrieveCapabilityForTechnologyUseCaseImpl(
            CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort) {
        this.capabilityForTechnologyRepositoryPort = capabilityForTechnologyRepositoryPort;
    }

    @Override
    public Flux<CapabilityForTechnology> findByCapabilityId(Long capabilityId) {
        return this.capabilityForTechnologyRepositoryPort.findAllByCapabilityId(capabilityId);
    }

    @Override
    public Mono<CapabilityForTechnology> findByCapabilityIdAndTechnologyId(Long capabilityId, Long technologyId) {
        return this.capabilityForTechnologyRepositoryPort.findByCapabilityIdAndTechnologyId(capabilityId, technologyId);
    }

}
