package com.capabilities.domain.ports.in;

import com.capabilities.domain.models.CapabilityForTechnology;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetrieveCapabilityForTechnologyUseCase {
    Mono<CapabilityForTechnology> findByCapabilityIdAndTechnologyId(Long capabilityId, Long technologyId);
    Flux<CapabilityForTechnology> findByCapabilityId(Long capabilityId);
}
