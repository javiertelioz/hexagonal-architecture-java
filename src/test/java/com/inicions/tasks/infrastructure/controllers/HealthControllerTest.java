package com.inicions.tasks.infrastructure.controllers;

import com.inicions.tasks.TasksApplication;

import com.inicions.tasks.infrastructure.config.SecurityConfig;
import com.inicions.tasks.infrastructure.security.JwtAuthorizationFilter;
import com.inicions.tasks.infrastructure.security.jwt.JwtUtils;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles(value = "test")
@WebMvcTest(controllers = HealthController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = {SecurityConfig.class, JwtAuthorizationFilter.class}
                )
        }
)
public class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtils jwtUtils;

    @Test
    @WithMockUser
    public void healthEndpoint_ReturnsApplicationStatus() throws Exception {
        mockMvc.perform(get("/health")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("successfully"))
                .andExpect(jsonPath("$.applicationName").value("Nombre de tu Aplicación"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }
}
