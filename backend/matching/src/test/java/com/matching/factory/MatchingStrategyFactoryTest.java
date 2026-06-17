package com.matching.factory;

import com.matching.strategy.MatchingStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MatchingStrategyFactoryTest {

    private MatchingStrategyFactory factory;

    @BeforeEach
    void setUp() throws Exception {

        factory = new MatchingStrategyFactory();

        MatchingStrategy strategyMock =
                mock(MatchingStrategy.class);

        Map<String, MatchingStrategy> estrategias =
                new HashMap<>();

        estrategias.put("default", strategyMock);

        Field field =
                MatchingStrategyFactory.class
                        .getDeclaredField("estrategias");

        field.setAccessible(true);
        field.set(factory, estrategias);
    }

    @Test
    void debeRetornarEstrategiaExistente() {

        MatchingStrategy estrategia =
                factory.getEstrategia("default");

        assertNotNull(estrategia);
    }

    @Test
    void debeLanzarExcepcionCuandoNoExiste() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.getEstrategia("inexistente")
        );
    }
}