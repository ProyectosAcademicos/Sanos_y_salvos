package com.example.bffservice.DashboardDTO;

import com.example.bffservice.MascotaDTO.MascotaDTO;
import com.example.bffservice.NotificacionDTO.NotificacionDTO;
import com.example.bffservice.Usuario;
import lombok.Data;

@Data
public class DashboardDTO {

    private Usuario usuario;

    private List<MascotaDTO> mascotas;

    private List<NotificacionDTO> notificaciones;

}
