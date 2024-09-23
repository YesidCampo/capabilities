package com.capabilities.infrastruture.repositories;

import org.springframework.stereotype.Component;

import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.ports.out.CapabilityForTechnologyRepositoryPort;
import com.capabilities.infrastruture.utils.CapabilityForTechnologyMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CapabilityForTechnologyAdapter implements CapabilityForTechnologyRepositoryPort {

    private final CapabilityForTechnologyRepository capabilityForTechnologyRepository;

    public CapabilityForTechnologyAdapter(CapabilityForTechnologyRepository capabilityForTechnologyRepository) {
        this.capabilityForTechnologyRepository = capabilityForTechnologyRepository;
    }

    @Override
    public Mono<CapabilityForTechnology> save(CapabilityForTechnology capabilityForTechnology) {
        return this.capabilityForTechnologyRepository
                .save (CapabilityForTechnologyMapper.fromDomainModel(capabilityForTechnology))
                .map(CapabilityForTechnologyMapper::toDomainModel);
    }

    @Override
    public Flux<CapabilityForTechnology> findAllByCapabilityId(Long capabilityId) {
        return this.capabilityForTechnologyRepository.findAllByCapabilityId(capabilityId)
                .map(CapabilityForTechnologyMapper::toDomainModel);
    }

    @Override
    public Mono<CapabilityForTechnology> findByCapabilityIdAndTechnologyId(Long capabilityId, Long technologyId) {
        return this.capabilityForTechnologyRepository.findByCapabilityIdAndTechnologyId(capabilityId, technologyId)
                .map(CapabilityForTechnologyMapper::toDomainModel);
    }

}
