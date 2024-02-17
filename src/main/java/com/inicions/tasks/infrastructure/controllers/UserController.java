package com.inicions.tasks.infrastructure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.application.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user",
            description = "Create a user object. The response is user object with id, username, lastname, email and status.",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")}
    )
    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<com.inicions.tasks.infrastructure.response.ApiResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        com.inicions.tasks.infrastructure.response.ApiResponse<User> response = new com.inicions.tasks.infrastructure.response.ApiResponse<>(createdUser, "User created successfully.");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
