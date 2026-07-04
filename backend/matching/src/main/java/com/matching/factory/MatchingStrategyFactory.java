    // package com.matching.factory;

    // import org.springframework.beans.factory.annotation.Autowired;
    // import org.springframework.stereotype.Component;

    // import com.matching.strategy.MatchingStrategy;

    // import java.util.Map;

    // @Component
    // public class MatchingStrategyFactory {

    //     // Spring inyecta automáticamente todas las estrategias en este Map gracias a los nombres de los componentes
    //     @Autowired
    //     private Map<String, MatchingStrategy> estrategias;

    //     public MatchingStrategy getEstrategia(String tipoEstrategia) {
    //         MatchingStrategy estrategia = estrategias.get(tipoEstrategia);
    //         if (estrategia == null) {
    //             throw new IllegalArgumentException("Estrategia de matching no soportada: " + tipoEstrategia);
    //         }
    //         return estrategia;
    //     }
    // }
