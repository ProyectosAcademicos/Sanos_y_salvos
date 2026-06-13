import { it, expect, vi } from "vitest";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";

import RegistroMascota from "../src/componentes/RegistroMascota/RegistroMascota";
import { registrarMascota } from "../src/services/mascotaService";

vi.mock("../src/services/mascotaService", () => ({ // acá hago un mock del servicio de registro de mascota para evitar llamadas reales a la API
  registrarMascota: vi.fn()
}));

const mockNavigate = vi.fn();

vi.mock("react-router-dom", async () => { // acá hago un mock de useNavigate para evitar redirecciones reales durante las pruebas
  const actual = await vi.importActual("react-router-dom");

  return {
    ...actual,
    useNavigate: () => mockNavigate
  };
});

it("llama a registrarMascota, llena el formulario, lo envía y redirige a home-cliente", async () => {

    registrarMascota.mockResolvedValue({
        message: "Mascota registrada exitosamente"
    });

    const alertMock = vi
    .spyOn(window, "alert")
    .mockImplementation(() => {});
    
    render(
        <MemoryRouter>
        <RegistroMascota />
        </MemoryRouter>
    );

    fireEvent.change(screen.getByPlaceholderText("Nombre de la mascota"), {
        target: { value: "Firulais" }
    });

  fireEvent.change(screen.getByPlaceholderText("Tipo"), {
    target: { value: "Perro" }
  });

  fireEvent.change(screen.getByPlaceholderText("Raza"), {
    target: { value: "Labrador" }
  });

  fireEvent.change(screen.getByPlaceholderText("Edad"), {
    target: { value: "3" }
  });

  fireEvent.change(screen.getByPlaceholderText("Tamaño"), {
    target: { value: "Grande" }
  });

  fireEvent.change(screen.getByPlaceholderText("Descripción"), {
    target: { value: "Un perro muy amigable" }
  });

  fireEvent.click(screen.getByText("Registrar"));

  await waitFor(() => {
    expect(registrarMascota).toHaveBeenCalledWith(
      "Firulais",
      "Perro",
      "Labrador",
      "3",
      "Grande",
      "Un perro muy amigable"
    );
    expect(alertMock).toHaveBeenCalledWith("Mascota registrada exitosamente");
    expect(mockNavigate).toHaveBeenCalledWith("/home-cliente");
  });
});

vi.mock("react-router-dom", async () => { // acá hago un mock de useNavigate para evitar redirecciones reales durante las pruebas
  const actual = await vi.importActual("react-router-dom");

  return {
    ...actual,
    useNavigate: () => mockNavigate
  };
});

it("muestra error al fallar el registro", async () => {

    registrarMascota.mockRejectedValue(new Error("Error de registro"));

    const alertMock = vi
    .spyOn(window, "alert")
    .mockImplementation(() => {});
    
    render(
        <MemoryRouter>
        <RegistroMascota />
        </MemoryRouter>
    );

    fireEvent.change(screen.getByPlaceholderText("Nombre de la mascota"), {
        target: { value: "Firulais" }
    });

  fireEvent.change(screen.getByPlaceholderText("Tipo"), {
    target: { value: "Perro" }
  });

  fireEvent.change(screen.getByPlaceholderText("Raza"), {
    target: { value: "Labrador" }
  });

  fireEvent.change(screen.getByPlaceholderText("Edad"), {
    target: { value: "3" }
  });

  fireEvent.change(screen.getByPlaceholderText("Tamaño"), {
    target: { value: "Grande" }
  });

  fireEvent.change(screen.getByPlaceholderText("Descripción"), {
    target: { value: "Un perro muy amigable" }
  });

  fireEvent.click(screen.getByText("Registrar"));

  await waitFor(() => {
    expect(registrarMascota).toHaveBeenCalledWith(
      "Firulais",
      "Perro",
      "Labrador",
      "3",
      "Grande",
      "Un perro muy amigable"
    );
    expect(alertMock).toHaveBeenCalledWith("Error al registrar mascota: Error de registro");
    expect(console.error).toHaveBeenCalledWith("Error al registrar mascota:", new Error("Error de registro"));
  });
});

vi.spyOn(console, "error").mockImplementation(() => {}); // mockeo console.error para evitar que los errores se muestren en la consola durante las pruebas