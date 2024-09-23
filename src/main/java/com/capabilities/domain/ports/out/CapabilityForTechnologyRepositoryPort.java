package com.capabilities.domain.ports.out;

import com.capabilities.domain.models.CapabilityForTechnology;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapabilityForTechnologyRepositoryPort {

    Mono<CapabilityForTechnology> save(CapabilityForTechnology capabilityForTechnology);

    Flux<CapabilityForTechnology> findAllByCapabilityId(Long capabilityId);

    Mono<CapabilityForTechnology> findByCapabilityIdAndTechnologyId(Long capabilityId, Long technologyId);

}
