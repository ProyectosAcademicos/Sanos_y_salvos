import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import { vi } from "vitest";

import ReporteContenido from "../src/componentes/ReporteContenido/ReporteContenido";
import * as reporteService from "../src/services/reporteService";

const mockNavigate = vi.fn();

vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual("react-router-dom");

  return {
    ...actual,
    useNavigate: () => mockNavigate
  };
});

vi.mock("../src/services/reporteService", () => ({
  registrarReporte: vi.fn()
}));

describe("ReporteContenido", () => {

  beforeEach(() => {
    vi.clearAllMocks();

    window.alert = vi.fn();
  });

  test("llama al servicio registrarReporte", async () => {

    reporteService.registrarReporte.mockResolvedValue({});

    render(
      <MemoryRouter>
        <ReporteContenido />
      </MemoryRouter>
    );

    fireEvent.change(
      screen.getByPlaceholderText(
        "Última ubicación vista (Comuna/Calle)"
      ),
      {
        target: {
          value: "Pedro Aguirre Cerda"
        }
      }
    );

    fireEvent.change(
      screen.getByPlaceholderText(
        "Fecha de desaparición"
      ),
      {
        target: {
          value: "2026-06-15"
        }
      }
    );

    fireEvent.change(
      screen.getByPlaceholderText(
        "Descripción adicional (color de collar, señas particulares...)"
      ),
      {
        target: {
          value: "Gato negro"
        }
      }
    );

    fireEvent.click(
      screen.getByText("Informar")
    );

    await waitFor(() => {

      expect(
        reporteService.registrarReporte
      ).toHaveBeenCalled();

    });

  });

  test("muestra alerta de éxito", async () => {

    reporteService.registrarReporte.mockResolvedValue({});

    render(
      <MemoryRouter>
        <ReporteContenido />
      </MemoryRouter>
    );

    fireEvent.click(
      screen.getByText("Informar")
    );

    await waitFor(() => {

      expect(window.alert)
        .toHaveBeenCalledWith(
          "Reporte realizado correctamente"
        );

    });

  });

  test("navega al home", async () => {

    reporteService.registrarReporte.mockResolvedValue({});

    render(
      <MemoryRouter>
        <ReporteContenido />
      </MemoryRouter>
    );

    fireEvent.click(
      screen.getByText("Informar")
    );

    await waitFor(() => {

      expect(mockNavigate)
        .toHaveBeenCalledWith("/home");

    });

  });

  test("limpia formulario", () => {

    render(
      <MemoryRouter>
        <ReporteContenido />
      </MemoryRouter>
    );

    const ubicacion = screen.getByPlaceholderText(
      "Última ubicación vista (Comuna/Calle)"
    );

    fireEvent.change(ubicacion, {
      target: {
        value: "Santiago Centro"
      }
    });

    expect(ubicacion.value)
      .toBe("Santiago Centro");

    fireEvent.click(
      screen.getByText("Limpiar")
    );

    expect(ubicacion.value)
      .toBe("");

  });

});