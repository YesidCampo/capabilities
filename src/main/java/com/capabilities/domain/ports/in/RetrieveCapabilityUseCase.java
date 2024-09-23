package com.capabilities.domain.ports.in;



import org.springframework.data.domain.Pageable;

import com.capabilities.domain.models.Capability;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetrieveCapabilityUseCase {
    Mono<Capability> getCapabilityByName(String name);

    Flux<Capability> getAllCapability(Pageable pageable, boolean ascendingByName, boolean ascendingByTechnologynumber);
}
