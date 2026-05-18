package com.mascotas.strategy;

import com.mascotas.model.Mascota;

public interface EstadoMascotaStrategy {
    void ejecutar(Mascota mascota);
    String getEstado();
}