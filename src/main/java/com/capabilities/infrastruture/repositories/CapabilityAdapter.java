package com.capabilities.infrastruture.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.capabilities.domain.models.Capability;
import com.capabilities.domain.ports.out.CapabilityRepositoryPort;
import com.capabilities.infrastruture.utils.CapabilityMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CapabilityAdapter implements CapabilityRepositoryPort {

    private final CapabilityRepository capabilityRepository;

    public CapabilityAdapter(CapabilityRepository capabilityRepository) {
        this.capabilityRepository = capabilityRepository;
    }

    @Override
    public Mono<Capability> save(Capability capability) {
        return this.capabilityRepository.save(CapabilityMapper.fromDomainModel(capability))
                .map(CapabilityMapper::toDomainModel);
    }

    @Override
    public Mono<Capability> findByName(String name) {
        return this.capabilityRepository.findByName(name).map(CapabilityMapper::toDomainModel);
    }

    @Override
    public Flux<Capability> findAll(Pageable pageable, boolean ascendingByName, boolean ascendingByTechnologynumber) {
        return this.capabilityRepository.findAll()
                .map(CapabilityMapper::toDomainModel);
    }

}
