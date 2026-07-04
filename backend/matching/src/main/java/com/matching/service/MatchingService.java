package com.matching.service;

import com.matching.model.Matching;
import com.matching.repository.MatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.matching.dto.MatchingDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String REPORTES_URL = "http://reportes-service:8080/reportes";
    private final String NOTIFICACIONES_URL = "http://notificaciones-service:8080/api/notificaciones/evento/match";

    private MatchingDTO convertirADTO(Matching matching) {

        MatchingDTO dto = new MatchingDTO();

        dto.setId(matching.getId());
        dto.setRutUsuario(matching.getRutUsuario());
        dto.setIdReportePerdida(matching.getIdReportePerdida());
        dto.setIdReporteEncontrado(matching.getIdReporteEncontrado());
        dto.setFecha(matching.getFecha());

        return dto;
    }

    // EVENTO PRINCIPAL
    public void procesarEventoReporteCreado(String idReporte, String tipoReporte, String rutUsuario) {

        System.out.println("📩 Evento recibido en Matching: " + tipoReporte);

        // SOLO ACTUAMOS SI ES ENCONTRADA
        if (!"ENCONTRADA".equalsIgnoreCase(tipoReporte)) {
            return;
        }

        // 1. Obtener todas las pérdidas
        ReporteDTO[] reportes = restTemplate.getForObject(
                REPORTES_URL + "/activos",
                ReporteDTO[].class
        );

        if (reportes == null || reportes.length == 0) {
            return;
        }

        List<ReporteDTO> perdidas = Arrays.stream(reportes)
                .filter(r -> "PERDIDA".equalsIgnoreCase(r.getTipoReporte()))
                .toList();

        // 2. Obtener reporte encontrado (DIRECTO)
        ReporteDTO encontrado = restTemplate.getForObject(
                REPORTES_URL + "/" + idReporte,
                ReporteDTO.class
        );

        if (encontrado == null) {
            return;
        }

        String ubicacionEncontrada = encontrado.getUbicacionPerdida();

        for (ReporteDTO perdida : perdidas) {

            if (perdida.getUbicacionPerdida() == null || ubicacionEncontrada == null) {
                continue;
            }

            String ubicacionPerdida = perdida.getUbicacionPerdida().trim();
            String ubicacionEncontradaNormalizada = ubicacionEncontrada.trim();

            System.out.println(
                    "Comparando -> Perdida: "
                            + ubicacionPerdida
                            + " | Encontrada: "
                            + ubicacionEncontradaNormalizada
            );

            if (!ubicacionPerdida.equalsIgnoreCase(ubicacionEncontradaNormalizada)) {
                continue;
            }

            System.out.println(
                    " MATCH ENCONTRADO entre "
                            + perdida.getIdReporte()
                            + " y "
                            + idReporte
            );

            Matching match = new Matching();
            match.setRutUsuario(perdida.getRutUsuario());
            match.setIdReportePerdida(perdida.getIdReporte());
            match.setIdReporteEncontrado(idReporte);
            match.setFecha(LocalDateTime.now());

            Matching guardado = matchingRepository.save(match);

            enviarNotificacion(guardado, perdida);
        }
    }

    private void enviarNotificacion(Matching match, ReporteDTO reportePerdida) {

        String mensaje = "🔔 Posible coincidencia encontrada. " +
                "Se reportó una mascota encontrada cerca de la ubicación donde perdiste a tu mascota.";

        String url = NOTIFICACIONES_URL +
                "?idUsuario=" + match.getRutUsuario() +
                "&idMatch=" + match.getId() +
                "&mensaje=" + mensaje;

        restTemplate.postForObject(url, null, Void.class);
    }

    // DTO interno simple para comunicación entre microservicios
    static class ReporteDTO {
        private String idReporte;
        private String rutUsuario;
        private String tipoReporte;
        private String ubicacionPerdida;

        public String getIdReporte() { return idReporte; }
        public void setIdReporte(String idReporte) { this.idReporte = idReporte; }

        public String getRutUsuario() { return rutUsuario; }
        public void setRutUsuario(String rutUsuario) { this.rutUsuario = rutUsuario; }

        public String getTipoReporte() { return tipoReporte; }
        public void setTipoReporte(String tipoReporte) { this.tipoReporte = tipoReporte; }

        public String getUbicacionPerdida() { return ubicacionPerdida; }
        public void setUbicacionPerdida(String ubicacionPerdida) { this.ubicacionPerdida = ubicacionPerdida; }
    }

    public List<MatchingDTO> obtenerMatchesPorUsuario(String rutUsuario) {

    return matchingRepository.findByRutUsuario(rutUsuario)
            .stream()
            .map(this::convertirADTO)
            .toList();
    }
}