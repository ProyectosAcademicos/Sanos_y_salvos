package com.notificaciones.service;

import com.notificaciones.dto.NotificacionDTO;
import com.notificaciones.factory.NotificacionStrategyFactory;
import com.notificaciones.model.Notificacion;
import com.notificaciones.repository.NotificacionRepository;
import com.notificaciones.strategy.NotificacionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private NotificacionStrategyFactory factory;

    // --- CONVERSIONES ---
    private NotificacionDTO convertirADTO(Notificacion n) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(n.getId());
        dto.setIdUsuario(n.getIdUsuario());
        dto.setIdMatch(n.getIdMatch());
        dto.setTipo(n.getTipo());
        dto.setMensaje(n.getMensaje());
        dto.setLeido(n.getLeido());
        dto.setFechaEnvio(n.getFechaEnvio());
        return dto;
    }

    // Simula recibir el evento "MatchEncontrado" desde Matching
    public NotificacionDTO procesarEventoMatchEncontrado(
            String idUsuario, String idMatch, String mensaje) {

        log.info("📨 EVENTO RECIBIDO: MatchEncontrado para usuario: {}", idUsuario);

        Notificacion notificacion = new Notificacion();
        notificacion.setIdUsuario(idUsuario);
        notificacion.setIdMatch(idMatch);
        notificacion.setTipo("MATCH");
        notificacion.setMensaje(mensaje);
        notificacion.setLeido(false);
        notificacion.setFechaEnvio(new Date());

        // Factory obtiene la estrategia correcta
        NotificacionStrategy strategy = factory.obtener("MATCH");
        // Strategy ejecuta el envío simulado
        strategy.enviar(notificacion);

        return convertirADTO(notificacionRepository.save(notificacion));
    }

    // Marcar como leída
    public NotificacionDTO marcarComoLeida(Long id) {
        return notificacionRepository.findById(id).map(n -> {
            n.setLeido(true);
            return convertirADTO(notificacionRepository.save(n));
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
    }

    // Obtener todas las notificaciones de un usuario
    public List<NotificacionDTO> obtenerPorUsuario(String idUsuario) {
        return notificacionRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    // Eliminar notificación
    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}