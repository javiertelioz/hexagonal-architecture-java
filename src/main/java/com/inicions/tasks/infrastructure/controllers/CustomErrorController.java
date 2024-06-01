package com.inicions.tasks.infrastructure.controllers;

import jakarta.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.web.servlet.error.ErrorController;

import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @Operation(description = "This method is used to get the current date.", hidden = true)
    @RequestMapping("/error")
    public Map<String, Object> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (status != null) {
            try {
                int statusCode = Integer.parseInt(status.toString());
                httpStatus = HttpStatus.valueOf(statusCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> body = new HashMap<>();
        body.put("status", httpStatus.value());
        body.put("error", httpStatus.getReasonPhrase());
        body.put("message", "There was an error in the application.");
        return body;
    }


    public String getErrorPath() {
        return "/error";
    }
}