package com.capabilities.infrastruture.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capabilities.application.services.CapabilityService;
import com.capabilities.domain.models.Capability;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/capability")
public class CapabilityController {

    private final CapabilityService capabilityService;

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }

    @PostMapping("/")
    @Operation(summary = "Create Tecnology", description = "Save capability in the system. **Warning:**  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Capability saved correctly"),
            @ApiResponse(responseCode = "400", description = "Bad request: Capability Not Created or Bad request: The field is empty or capability not created due to duplicate name or The field is not valid format", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Mono<Capability> createCapability(
            @RequestBody @Schema(example = "{\"name\": \"Desarrollador\",\"description\": \"Desarrollador\"}") Capability capability) {
        return  this.capabilityService.createCapability(capability);
    }

}
