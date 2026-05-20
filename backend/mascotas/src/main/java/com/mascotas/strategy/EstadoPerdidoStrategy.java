package com.mascotas.strategy;

import com.mascotas.model.Mascota;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EstadoPerdidoStrategy implements EstadoMascotaStrategy {

    @Override
    public void ejecutar(Mascota mascota) {
        mascota.setEstado("Perdido");
        log.info("🔴 Mascota '{}' marcada como Perdida. Se debe generar reporte.", mascota.getNombre());
    }

    @Override
    public String getEstado() {
        return "Perdido";
    }
}