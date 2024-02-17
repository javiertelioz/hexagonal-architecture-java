package com.inicions.tasks.infrastructure.security.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilsTest {
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();

        String secureSecretKey = "claveSecretaMuyLargaYComplejaQueCumpleConLosRequisitosDeSeguridad";
        ReflectionTestUtils.setField(jwtUtils, "secretKey", secureSecretKey);
        ReflectionTestUtils.setField(jwtUtils, "timeExpiration", "3600000");
    }

    @Test
    void generateAccessToken_ShouldGenerateValidToken() {
        String username = "testUser";
        String token = jwtUtils.generateAccessToken(username);

        assertNotNull(token);
        assertTrue(jwtUtils.isTokenValid(token));
        assertEquals(username, jwtUtils.getUsernameFromToken(token));
    }

    @Test
    void isTokenValid_WithValidToken_ShouldReturnTrue() {
        String username = "testUser";
        String token = jwtUtils.generateAccessToken(username);

        assertTrue(jwtUtils.isTokenValid(token));
    }

    @Test
    void isTokenValid_WithInvalidToken_ShouldReturnFalse() {
        String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0VXNlciIsImlhdCI6MTYwOTU3ODI2NSwiZXhwIjoxNjA5NTgwMDY1fQ.invalid_signature";

        assertFalse(jwtUtils.isTokenValid(invalidToken));
    }

    @Test
    void getUsernameFromToken_ShouldReturnUsername() {
        String username = "testUser";
        String token = jwtUtils.generateAccessToken(username);

        String extractedUsername = jwtUtils.getUsernameFromToken(token);

        assertEquals(username, extractedUsername);
    }

}
