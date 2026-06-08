package com.mascotas.service;

import com.mascotas.model.Mascota;
import com.mascotas.repository.MascotaRepository;
import com.mascotas.strategy.EstadoMascotaStrategy;
import com.mascotas.dto.MascotaDTO;
import com.mascotas.factory.EstadoMascotaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.mascotas.config.JwtUtil;

@Service
public class MascotaService {

    @Autowired
    private JwtUtil jwtUtil; //  Inyectamos el JwtUtil para validar el token y extraer el rut

    @Autowired
    private MascotaRepository mascotaRepository; 

    @Autowired
    private EstadoMascotaFactory estadoFactory; // ✅ Factory inyectada

    // --- CONVERSIONES ---
    private MascotaDTO convertirADTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId()); 
        dto.setNombre(mascota.getNombre());
        dto.setTipo(mascota.getTipo());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());
        dto.setTamaño(mascota.getTamaño());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setIdUsuario(mascota.getIdUsuario());
        dto.setEstado(mascota.getEstado());
        return dto;
    }

    private Mascota convertirAEntidad(MascotaDTO dto) {
        Mascota mascota = new Mascota();
        if (dto.getId() != null) mascota.setId(dto.getId());
        mascota.setNombre(dto.getNombre());
        mascota.setTipo(dto.getTipo());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setTamaño(dto.getTamaño());
        mascota.setDescripcion(dto.getDescripcion());
        //mascota.setIdUsuario(rut);
        mascota.setEstado(dto.getEstado());
        return mascota;
    }

    // --- CRUD ---
    public List<MascotaDTO> listarTodas() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MascotaDTO guardar(
            MascotaDTO dto,
            String authHeader
    ) {

        String token =
                authHeader.replace("Bearer ", "");

        String rut =
                jwtUtil.extractUsername(token);

        Mascota mascota = convertirAEntidad(dto);

        mascota.setIdUsuario(rut);

        if (mascota.getEstado() == null) {
            mascota.setEstado("Sano y Salvo");
        }

        return convertirADTO(
                mascotaRepository.save(mascota)
        );
    }

    public Optional<MascotaDTO> obtenerPorId(Long id) {
        return mascotaRepository.findById(id).map(this::convertirADTO);
    }

    public MascotaDTO actualizar(Long id, MascotaDTO dto) {
        return mascotaRepository.findById(id).map(mascota -> {
            mascota.setNombre(dto.getNombre());
            mascota.setTipo(dto.getTipo());
            mascota.setRaza(dto.getRaza());
            mascota.setEdad(dto.getEdad());
            mascota.setTamaño(dto.getTamaño());
            mascota.setDescripcion(dto.getDescripcion());
            mascota.setEstado(dto.getEstado());
            mascota.setIdUsuario(dto.getIdUsuario());
            return convertirADTO(mascotaRepository.save(mascota));
        }).orElseThrow(() -> new RuntimeException("No encontrada"));
    }

    // ✅ Solo UNA versión, con Factory y Strategy
    public MascotaDTO actualizarEstado(Long id, String nuevoEstado) {
        return mascotaRepository.findById(id).map(mascota -> {
            EstadoMascotaStrategy strategy = estadoFactory.obtener(nuevoEstado);
            strategy.ejecutar(mascota);
            return convertirADTO(mascotaRepository.save(mascota));
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        mascotaRepository.deleteById(id);
    }
}