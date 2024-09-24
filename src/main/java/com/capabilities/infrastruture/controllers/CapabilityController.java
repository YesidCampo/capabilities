package com.capabilities.infrastruture.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capabilities.application.services.CapabilityService;
import com.capabilities.domain.models.Capability;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Operation(summary = "Create Capability", description = "Save capability in the system. **Warning:**  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Capability saved correctly"),
            @ApiResponse(responseCode = "400", description = "Bad request: Capability Not Created or Bad request: The field is empty or capability not created due to duplicate name or The field is not valid format", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Mono<Capability> createCapability(
            @RequestBody @Schema(example = "{\"name\": \"Desarrollador\",\"description\": \"Desarrollador\"}") Capability capability) {
        return this.capabilityService.createCapability(capability);
    }

    @GetMapping("/capabilities")
    @Operation(summary = "Get All capabilities", description = "Return all capabilities of system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "capabilities data found."),
            @ApiResponse(responseCode = "400", description = "Bad request: Not found capabilities", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Flux<Capability> getAllTecnology(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean ascendingName,
            @RequestParam(defaultValue = "true") boolean ascendingTechnologiNumber) {
        Pageable pageable = PageRequest.of(page, size);
        return this.capabilityService.getAllCapability(pageable, ascendingName, ascendingTechnologiNumber);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Capability", description = "Return Capability by Id of system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capability data found."),
            @ApiResponse(responseCode = "400", description = "Bad request: Not found Capability", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Mono<Capability> getTecnologyById(@PathVariable Long id) {
        return this.capabilityService.getCapabilityById(id);
    }

    @PostMapping("/capabilities/ids")
    @Operation(summary = "Get All Capabilities By Ids", description = "Return all Capabilities of system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capabilities data found."),
            @ApiResponse(responseCode = "400", description = "Bad request: Not found Technologies", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
    })
    public Flux<Capability> getAllTecnologyByIds(@RequestBody List<Long> ids) {
        return this.capabilityService.getCapabilitiesByIds(ids);
    }

}
