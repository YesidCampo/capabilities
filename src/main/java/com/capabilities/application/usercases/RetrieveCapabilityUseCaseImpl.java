package com.capabilities.application.usercases;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.capabilities.domain.models.Capability;
import com.capabilities.domain.ports.in.RetrieveCapabilityUseCase;
import com.capabilities.domain.ports.out.CapabilityRepositoryPort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RetrieveCapabilityUseCaseImpl implements RetrieveCapabilityUseCase {

    private final CapabilityRepositoryPort capabilityRepositoryPort;

    public RetrieveCapabilityUseCaseImpl(CapabilityRepositoryPort capabilityRepositoryPort) {
        this.capabilityRepositoryPort = capabilityRepositoryPort;
    }

    @Override
    public Mono<Capability> getCapabilityByName(String name) {
        return this.capabilityRepositoryPort.findByName(name);
    }

    @Override
    public Flux<Capability> getAllCapability(Pageable pageable, boolean ascendingByName,
            boolean ascendingByTechnologynumber) {
        return this.capabilityRepositoryPort.findAll(pageable, ascendingByName, ascendingByTechnologynumber);
    }

    @Override
    public Mono<Capability> getCapabilityById(Long id) {
        return this.capabilityRepositoryPort.findById(id);
    }

    @Override
    public Flux<Capability> getCapabilitiesByIds(List<Long> ids) {
        return this.capabilityRepositoryPort.findAllById(ids);
    }

}
