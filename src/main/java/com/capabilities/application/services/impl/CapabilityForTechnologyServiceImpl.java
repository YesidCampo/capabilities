package com.capabilities.application.services.impl;

import com.capabilities.application.services.CapabilityForTechnologyService;
import com.capabilities.domain.exception.InvalidCapabilityException;
import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.ports.in.CreateCapabilityForTechnologyUseCase;
import com.capabilities.domain.ports.in.RetrieveCapabilityForTechnologyUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapabilityForTechnologyServiceImpl implements CapabilityForTechnologyService {

    private final CreateCapabilityForTechnologyUseCase createCapabilityForTechnologyUseCase;
    private final RetrieveCapabilityForTechnologyUseCase retrieveCapabilityForTechnologyUseCase;

    public CapabilityForTechnologyServiceImpl(CreateCapabilityForTechnologyUseCase createCapabilityForTechnologyUseCase,
            RetrieveCapabilityForTechnologyUseCase retrieveCapabilityForTechnologyUseCase) {
        this.createCapabilityForTechnologyUseCase = createCapabilityForTechnologyUseCase;
        this.retrieveCapabilityForTechnologyUseCase = retrieveCapabilityForTechnologyUseCase;
    }

    @Override
    public Mono<CapabilityForTechnology> save(CapabilityForTechnology capabilityForTechnology) {
        return findByCapabilityIdAndTechnologyId(capabilityForTechnology.getCapabilityId(),
                capabilityForTechnology.getTechnologyId())
                .flatMap(capabilityForTechnologyExisting -> Mono.error(new InvalidCapabilityException("Techonology repeate!!")))
                .switchIfEmpty(this.createCapabilityForTechnologyUseCase.save(capabilityForTechnology)
                        .switchIfEmpty(Mono.error(new Exception("capabilityForTechnology not created"))))
                .map(CapabilityForTechnology.class::cast)
                .onErrorMap(Exception.class, ex -> new Exception(ex.getMessage()));
    }

    @Override
    public Flux<CapabilityForTechnology> findByCapabilityId(Long capabilityId) {
        return this.retrieveCapabilityForTechnologyUseCase.findByCapabilityId(capabilityId);
    }

    @Override
    public Mono<CapabilityForTechnology> findByCapabilityIdAndTechnologyId(Long capabilityId, Long technologyId) {
        return this.retrieveCapabilityForTechnologyUseCase.findByCapabilityIdAndTechnologyId(capabilityId,
                technologyId);
    }

}
