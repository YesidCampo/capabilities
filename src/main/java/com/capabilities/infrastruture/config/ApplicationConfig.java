package com.capabilities.infrastruture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.capabilities.application.services.CapabilityForTechnologyService;
import com.capabilities.application.services.CapabilityService;
import com.capabilities.application.services.impl.CapabilityForTechnologyServiceImpl;
import com.capabilities.application.services.impl.CapabilityServiceImpl;
import com.capabilities.application.usercases.CreateCapabilityForTechnologyUseCaseImpl;
import com.capabilities.application.usercases.CreateCapabilityUseCaseImpl;
import com.capabilities.application.usercases.RetrieveCapabilityForTechnologyUseCaseImpl;
import com.capabilities.application.usercases.RetrieveCapabilityUseCaseImpl;
import com.capabilities.domain.ports.out.CapabilityForTechnologyRepositoryPort;
import com.capabilities.domain.ports.out.CapabilityRepositoryPort;
import com.capabilities.infrastruture.repositories.CapabilityAdapter;
import com.capabilities.infrastruture.repositories.CapabilityForTechnologyAdapter;

@Configuration
public class ApplicationConfig {

    @Bean
    public CapabilityService capabilityService(CapabilityRepositoryPort capabilityRepositoryPort,
            CapabilityForTechnologyService capabilityForTechnologyService, WebClient webClient) {
        return new CapabilityServiceImpl(
                new CreateCapabilityUseCaseImpl(capabilityRepositoryPort),
                new RetrieveCapabilityUseCaseImpl(capabilityRepositoryPort),
                new CapabilityForTechnologyServiceImpl(capabilityForTechnologyService, capabilityForTechnologyService),
                webClient);
    }

    @Bean
    public CapabilityForTechnologyService capabilityForTechnologyService(
            CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort) {
        return new CapabilityForTechnologyServiceImpl(
                new CreateCapabilityForTechnologyUseCaseImpl(capabilityForTechnologyRepositoryPort),
                new RetrieveCapabilityForTechnologyUseCaseImpl(capabilityForTechnologyRepositoryPort));
    }

    @Bean
    public CapabilityForTechnologyRepositoryPort capabilityForTechnologyRepositoryPort(
            CapabilityForTechnologyAdapter capabilityForTechnologyAdapter) {
        return capabilityForTechnologyAdapter;
    }

    @Bean
    public CapabilityRepositoryPort capabilityRepositoryPort(CapabilityAdapter capabilityAdapter) {
        return capabilityAdapter;
    }
}
