package main.java.com.example.bffservice.DashboardController;


@RestController
@RequestMapping("/bff")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard/{rut}")
    public DashboardDTO obtenerDashboard(
            @PathVariable String rut) {

        return dashboardService.obtenerDashboard(rut);
    }
}