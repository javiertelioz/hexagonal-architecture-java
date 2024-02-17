package com.inicions.tasks.infrastructure.security.jwt;

import com.inicions.tasks.infrastructure.security.CustomAccessDeniedHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;

import static org.mockito.Mockito.verify;

public class CustomAccessDeniedHandlerTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private CustomAccessDeniedHandler accessDeniedHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accessDeniedHandler = new CustomAccessDeniedHandler();
    }

    @Test
    void whenAccessDenied_thenRespondWith403Forbidden() throws Exception {
        AccessDeniedException accessDeniedException = new AccessDeniedException("Access Denied");

        accessDeniedHandler.handle(request, response, accessDeniedException);

        verify(response).sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
}
