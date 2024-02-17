package com.inicions.tasks.infrastructure.adapters;

import com.inicions.tasks.domain.model.AdditionalTaskInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ExternalServiceAdapterTest {
    @Mock
    private RestTemplate restTemplate;

    private ExternalServiceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new ExternalServiceAdapter(restTemplate);
    }

    @Test
    void getAdditionalTaskInfo_ReturnsValidInfo() {
        Long taskId = 1L;
        ExternalServiceAdapter.JsonPlaceholderTodo mockTodo = new ExternalServiceAdapter.JsonPlaceholderTodo();
        mockTodo.setId(taskId);
        mockTodo.setUserId(1L);

        ExternalServiceAdapter.JsonPlaceholderUser mockUser = new ExternalServiceAdapter.JsonPlaceholderUser();
        mockUser.setId(1L);
        mockUser.setName("Leanne Graham");
        mockUser.setEmail("Sincere@april.biz");

        when(restTemplate.getForEntity(anyString(), eq(ExternalServiceAdapter.JsonPlaceholderTodo.class)))
                .thenReturn(ResponseEntity.ok(mockTodo));
        when(restTemplate.getForEntity(anyString(), eq(ExternalServiceAdapter.JsonPlaceholderUser.class)))
                .thenReturn(ResponseEntity.ok(mockUser));

        AdditionalTaskInfo result = adapter.getAdditionalTaskInfo(taskId);

        assertNotNull(result);
        assertEquals(mockUser.getId(), result.getUserId());
        assertEquals(mockUser.getName(), result.getUserName());
        assertEquals(mockUser.getEmail(), result.getUserEmail());
    }

    @Test
    void getAdditionalTaskInfo_WhenTodoIsNull_ShouldReturnNull() {
        Long taskId = 1L;

        when(restTemplate.getForEntity(anyString(), eq(ExternalServiceAdapter.JsonPlaceholderTodo.class)))
                .thenReturn(ResponseEntity.ok(null));

        AdditionalTaskInfo result = adapter.getAdditionalTaskInfo(taskId);

        assertNull(result, "El método debería retornar null cuando el cuerpo de la respuesta de la tarea es null");
    }


    @Test
    void getAdditionalTaskInfo_WhenUserIsNull_ShouldReturnNull() {
        Long taskId = 1L;
        ExternalServiceAdapter.JsonPlaceholderTodo mockTodo = new ExternalServiceAdapter.JsonPlaceholderTodo();
        mockTodo.setId(taskId);
        mockTodo.setUserId(1L);

        when(restTemplate.getForEntity(anyString(), eq(ExternalServiceAdapter.JsonPlaceholderTodo.class)))
                .thenReturn(ResponseEntity.ok(mockTodo));
        when(restTemplate.getForEntity(anyString(), eq(ExternalServiceAdapter.JsonPlaceholderUser.class)))
                .thenReturn(ResponseEntity.ok(null));

        AdditionalTaskInfo result = adapter.getAdditionalTaskInfo(taskId);

        assertNull(result, "El método debería retornar null cuando el cuerpo de la respuesta de la tarea es null");
    }
}
