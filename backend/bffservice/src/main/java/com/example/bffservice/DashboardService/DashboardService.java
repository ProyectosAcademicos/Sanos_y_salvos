package main.java.com.example.bffservice.DashboardService;


@Service
public class DashboardService {

    @Autowired
    private RestTemplate restTemplate;

    public DashboardDTO obtenerDashboard(String rut) {

        Usuario usuario =
                restTemplate.getForObject(
                        "http://usuarios-service:8080/usuarios/" + rut,
                        Usuario.class);

        List<MascotaDTO> mascotas =
                Arrays.asList(
                        restTemplate.getForObject(
                                "http://mascotas-service:8080/api/mascotas/usuario/" + rut,
                                MascotaDTO[].class));

        List<NotificacionDTO> notificaciones =
                Arrays.asList(
                        restTemplate.getForObject(
                                "http://notificaciones-service:8080/api/notificaciones/usuario/" + rut,
                                NotificacionDTO[].class));

        DashboardDTO dto = new DashboardDTO();

        dto.setUsuario(usuario);
        dto.setMascotas(mascotas);
        dto.setNotificaciones(notificaciones);

        return dto;
    }
}