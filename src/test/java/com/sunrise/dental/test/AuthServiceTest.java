package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.model.User;
import com.sunrise.dental.service.AuthService;

public class AuthServiceTest {

    @Test
    void testValidLogin() {

        AuthService authService = new AuthService();

        User user = authService.login("admin", "admin123");

        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }
    @Test
    void testInvalidLogin() {

        AuthService authService = new AuthService();

        User user = authService.login("admin", "wrongpassword");

        assertNull(user);
    }
}