package com.capabilities.infrastruture.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.capabilities.infrastruture.entities.CapabilityForTechnologyEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CapabilityForTechnologyRepository extends ReactiveCrudRepository<CapabilityForTechnologyEntity, Long> {

    Flux<CapabilityForTechnologyEntity> findAllByCapabilityId(Long capabilityId);

    @Query("SELECT * FROM capability_technology WHERE capability_id = :capabilityId AND technology_id = :technologyId")
    Mono<CapabilityForTechnologyEntity> findByCapabilityIdAndTechnologyId(@Param("capabilityId") Long capabilityId,
            @Param("technologyId") Long technologyId);

}
