package com.usuarios.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptStrategyTest {

    @Test
    void debeCodificarPassword() {

        BCryptStrategy strategy = new BCryptStrategy();

        String password = "1234";

        String hash = strategy.encode(password);

        assertNotNull(hash);
        assertNotEquals(password, hash);
        assertFalse(hash.isEmpty());
    }
}