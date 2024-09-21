package com.capabilities.domain.ports.in;



import com.capabilities.domain.models.Capability;

import reactor.core.publisher.Mono;

public interface RetrieveCapabilityUseCase {
    Mono<Capability> getCapabilityByName(String name);
}
