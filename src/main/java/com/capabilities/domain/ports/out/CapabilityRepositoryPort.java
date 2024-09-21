package com.capabilities.domain.ports.out;


import com.capabilities.domain.models.Capability;

import reactor.core.publisher.Mono;

public interface CapabilityRepositoryPort {

    Mono<Capability> save(Capability capability);

    Mono<Capability> findByName(String name);

}
