package com.capabilities.domain.ports.in;

import com.capabilities.domain.models.CapabilityForTechnology;

import reactor.core.publisher.Mono;

public interface CreateCapabilityForTechnologyUseCase {
    Mono<CapabilityForTechnology> save(CapabilityForTechnology capabilityForTechnology);
}
