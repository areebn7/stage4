package com.example.stage4;

import com.example.stage4.Controller.AuthController;
import com.example.stage4.Security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToken() {
        // Arrange
        String expectedToken = "mockedToken";
        when(tokenService.generateToken(authentication)).thenReturn(expectedToken);

        // Act
        String actualToken = authController.token(authentication);

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testHealth() {
        // Act
        String actualResponse = authController.health();

        // Assert
        assertEquals("Authorization Server is running.", actualResponse);
    }
}
