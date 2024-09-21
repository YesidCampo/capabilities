package com.capabilities.infrastruture.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.capabilities.infrastruture.entities.CapabilityEntity;

import reactor.core.publisher.Mono;

@Repository
public interface CapabilityRepository extends ReactiveCrudRepository<CapabilityEntity, Long> {

    Mono<CapabilityEntity> findByName(String name);
}
