package com.capabilities.domain.ports.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.capabilities.domain.models.Capability;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapabilityRepositoryPort {

    Mono<Capability> save(Capability capability);

    Mono<Capability> findByName(String name);

    Flux<Capability> findAll(Pageable pageable, boolean ascendingByName, boolean ascendingByTechnologynumber);

    Mono<Capability> findById(Long id);

    Flux<Capability> findAllById(List<Long> ids);
}
