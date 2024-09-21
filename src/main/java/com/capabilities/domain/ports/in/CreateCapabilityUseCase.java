package com.capabilities.domain.ports.in;



import com.capabilities.domain.models.Capability;

import reactor.core.publisher.Mono;

public interface CreateCapabilityUseCase {
    Mono<Capability> createCapability(Capability capability);
}
