package com.example.bffservice.controller;

import com.example.bffservice.dto.DashboardDTO;
import com.example.bffservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 


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