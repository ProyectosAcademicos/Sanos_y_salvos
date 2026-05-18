package com.mascotas.strategy;

import com.mascotas.model.Mascota;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EstadoEncontradoStrategy implements EstadoMascotaStrategy {

    @Override
    public void ejecutar(Mascota mascota) {
        mascota.setEstado("Encontrado");
        log.info("🟢 Mascota '{}' marcada como Encontrada. Notificar al dueño.", mascota.getNombre());
    }

    @Override
    public String getEstado() {
        return "Encontrado";
    }
}