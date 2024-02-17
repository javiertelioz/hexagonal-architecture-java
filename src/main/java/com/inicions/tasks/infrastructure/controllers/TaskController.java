package com.inicions.tasks.infrastructure.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inicions.tasks.application.services.TaskService;
import com.inicions.tasks.domain.model.AdditionalTaskInfo;
import com.inicions.tasks.domain.model.Task;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create a new task",
            description = "Create a task object. The response is task object with id, title, description and published status.",
            tags = {"Task"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<com.inicions.tasks.infrastructure.response.ApiResponse<Task>> createTask(@Valid @RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            com.inicions.tasks.infrastructure.response.ApiResponse<Task> response = new com.inicions.tasks.infrastructure.response.ApiResponse<>(createdTask, "Task created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get a task by its id",
            description = "Get a task object by specifying its id. The response is task object with id, title, description and published status.",
            tags = {"Task"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User not authenticated"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden - User does not have the necessary permissions"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Error processing the request")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        try {
            return taskService.getTaskById(id)
                    .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Get all tasks",
            description = "Get all tasks. The response is list of task object with id, title, description and published status.",
            tags = {"Task"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    @PreAuthorize("hasRole('INVITED')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a task by its id",
            description = "Update the details of a task specified by its id",
            tags = {"Task"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Delete a task by its id",
            description = "Delete a task specified by its id",
            tags = {"Task"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")}
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Get additional info for a task by its id",
            description = "Get additional info for a task specified by its id",
            tags = {"Task"}
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task info retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")}
    )
    @GetMapping(value = "/{id}/additional-info", produces = "application/json")
    public ResponseEntity<AdditionalTaskInfo> getAdditionalTaskInfo(@PathVariable Long id) {
        AdditionalTaskInfo additionalTaskInfo = taskService.getAdditionalTaskInfo(id);
        return new ResponseEntity<>(additionalTaskInfo, HttpStatus.OK);
    }
}
