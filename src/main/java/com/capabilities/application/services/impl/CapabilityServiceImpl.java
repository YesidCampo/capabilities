package com.capabilities.application.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.capabilities.application.services.CapabilityForTechnologyService;
import com.capabilities.application.services.CapabilityService;
import com.capabilities.domain.exception.InvalidCapabilityException;
import com.capabilities.domain.models.Capability;
import com.capabilities.domain.models.CapabilityForTechnology;
import com.capabilities.domain.models.Technology;
import com.capabilities.domain.ports.in.CreateCapabilityUseCase;
import com.capabilities.domain.ports.in.RetrieveCapabilityUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapabilityServiceImpl implements CapabilityService {

    private final CreateCapabilityUseCase createCapabilityUseCase;
    private final RetrieveCapabilityUseCase retrieveCapabilityUseCase;
    private final CapabilityForTechnologyService capabilityForTechnologyService;
    private final WebClient webClient;

    public CapabilityServiceImpl(CreateCapabilityUseCase createCapabilityUseCase,
            RetrieveCapabilityUseCase retrieveCapabilityUseCase,
            CapabilityForTechnologyService capabilityForTechnologyService, WebClient webClient) {
        this.createCapabilityUseCase = createCapabilityUseCase;
        this.retrieveCapabilityUseCase = retrieveCapabilityUseCase;
        this.capabilityForTechnologyService = capabilityForTechnologyService;
        this.webClient = webClient;
    }

    @Override
    public Mono<Capability> createCapability(Capability capability) {
        if (capability.getTechnologies().size() < 3) {
            return Mono.error(new InvalidCapabilityException("Debe tener mas de 3 relaciones de tegnologia"));
        }
        if (capability.getTechnologies().size() > 20) {
            return Mono.error(new InvalidCapabilityException("Debe tener menos de 20 relaciones de tecnologia"));
        }
        return this.createCapabilityUseCase.createCapability(capability)
                .flatMap(createdCapability -> Flux
                        .fromStream(capability.getTechnologies().stream().distinct())
                        .flatMap(technology -> this.capabilityForTechnologyService
                                .save(new CapabilityForTechnology(createdCapability.getId(),
                                        technology.getId()))
                                .switchIfEmpty(Mono
                                        .error(new Exception("CapabilityForTechnology not created")))
                                .then(Mono.just(technology)))
                        .collectList().flatMap(list -> {
                            createdCapability.setTechnologies(list);
                            return Mono.just(createdCapability);
                        }))
                .switchIfEmpty(Mono.error(new Exception("Capability not created")));
    }

    @Override
    public Mono<Capability> getCapabilityByName(String name) {
        return this.retrieveCapabilityUseCase.getCapabilityByName(name);
    }

    @Override
    public Flux<Capability> getAllCapability(Pageable pageable, boolean ascendingByName,
            boolean ascendingByTechnologynumber) {

        // getTecnologyById(1L)
        // .flatMap(technology -> Mono.error(new InvalidCapabilityException("Techonology
        // repeate!!")))
        // .subscribe();

        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);

        return this.retrieveCapabilityUseCase.getAllCapability(pageable, ascendingByName, ascendingByTechnologynumber)
                // .flatMap(capablitiyResult -> {
                //     this.capabilityForTechnologyService.findByCapabilityId(capablitiyResult.getId())
                //             .map(CapabilityForTechnology::getTechnologyId)
                //             .collectList()
                //             .flatMap(technologiesIds -> {
                //                 return getTecnologyAllByIds(technologiesIds).collectList().flatMap(techologies -> {
                //                     capablitiyResult.setTechnologies(techologies);
                //                     return capablitiyResult;
                //                 });
                //             });
                // })

                // .flatMap(getCapability-> {
                // this.capabilityForTechnologyService.findByCapabilityId(getCapability.getId())
                // .collectList()
                // .flatMap(getCapabilityForTechnology ->
                // Flux.fromStream(getCapabilityForTechnology)
                // .flatMap( item -> {
                // Technology technology = new Technology(item.getTechnologyId());
                // }
                // )
                // )
                // }
                // )

                .sort((cap1, cap2) -> {
                    Comparator<Capability> comparator = Comparator.comparing(Capability::getName);

                    // Ordenamiento por nombre ascendente o descendente
                    if (!ascendingByName) {
                        comparator = comparator.reversed();
                    }

                    // Ordenamiento por número de tecnologías (size)
                    Comparator<Capability> techNumberComparator = Comparator
                            .comparing(cap -> cap.getTechnologies().size());

                    // Si se pide ordenar por tamaño de tecnologías, aplicamos este comparador
                    if (!ascendingByTechnologynumber) {
                        techNumberComparator = techNumberComparator.reversed();
                    }

                    // Combinamos los dos comparadores: primero por nombre, luego por número de
                    // tecnologías
                    return comparator.thenComparing(techNumberComparator).compare(cap1, cap2);
                })
                .skip(pageable.getPageNumber() * pageable.getPageSize())
                .take(pageable.getPageSize());
    }

    public Mono<Technology> getTecnologyById(Long tecnologyId) {
        return webClient.get()
                .uri("/api/tecnology/" + tecnologyId)
                .retrieve()
                .bodyToMono(Technology.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Failed to retrieve technology")));
    }

    public Flux<Technology> getTecnologyAllByIds(List<Long> technologyIds) {
        return webClient.post() // Usamos POST porque vamos a enviar un RequestBody
                .uri("/api/tecnology/technologies/ids")
                .bodyValue(technologyIds) // Enviar la lista de IDs como RequestBody
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Technology>() {
                }) // Recibir una lista de objetos Technology
                .onErrorResume(e -> {
                    if (e instanceof WebClientResponseException.NotFound) {
                        return Flux.error(new RuntimeException("User with id:  not exist"));
                    }
                    return Flux.error(e);
                });
    }

}
