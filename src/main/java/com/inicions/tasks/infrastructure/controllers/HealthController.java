package com.inicions.tasks.infrastructure.controllers;

import com.inicions.tasks.infrastructure.response.HealthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Operation(
            summary = "Get service status",
            description = "Service Status",
            tags = {"Health"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return successfully response"),
    })
    @GetMapping(value = "/health", produces = "application/json")
    public ResponseEntity<HealthResponse> health() {

        String applicationName = "Nombre de tu Aplicación";
        String version = "1.0.0";
        HealthResponse response = new HealthResponse("successfully", applicationName, version);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
