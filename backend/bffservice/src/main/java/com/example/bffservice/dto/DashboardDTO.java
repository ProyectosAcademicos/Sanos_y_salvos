package com.example.bffservice.dto;

import com.example.bffservice.dto.*;
import lombok.Data;
import java.util.List;


@Data
public class DashboardDTO {

    private UsuarioDTO usuario;

    private List<MascotaDTO> mascotas;

    private List<NotificacionDTO> notificaciones;

}
