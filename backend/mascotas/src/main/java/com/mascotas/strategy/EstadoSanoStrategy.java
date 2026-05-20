package com.mascotas.strategy;

import com.mascotas.model.Mascota;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EstadoSanoStrategy implements EstadoMascotaStrategy {

    @Override
    public void ejecutar(Mascota mascota) {
        mascota.setEstado("Sano y Salvo");
        log.info("✅ Mascota '{}' marcada como Sana y Salva.", mascota.getNombre());
    }

    @Override
    public String getEstado() {
        return "Sano y Salvo";
    }
}