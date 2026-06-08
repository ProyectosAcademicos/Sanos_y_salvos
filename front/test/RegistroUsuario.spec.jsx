import { it, expect, vi } from "vitest";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";


import RegistroUsuario from "../src/componentes/RegistroContenido/RegistroContenido";
import { register } from "../src/services/registerService";

vi.mock("../src/services/registerService", () => ({ // acá hago un mock del servicio de registro para evitar llamadas reales a la API
  register: vi.fn()
}));

const mockNavigate = vi.fn();

vi.mock("react-router-dom", async () => { // acá hago un mock de useNavigate para evitar redirecciones reales durante las pruebas
  const actual = await vi.importActual("react-router-dom");

  return {
    ...actual,
    useNavigate: () => mockNavigate
  };
});

it("llama a registro y redirige a login", async () => {

    register.mockResolvedValue({
        token: "abc123"
    });

    const alertMock = vi
    .spyOn(window, "alert")
    .mockImplementation(() => {});
    
    render(
        <MemoryRouter>
        <RegistroUsuario />
        </MemoryRouter>
    );

    fireEvent.change(screen.getByPlaceholderText("Rut"), {
        target: { value: "12345678-9" }
    });

  fireEvent.change(screen.getByPlaceholderText("Nombre"), {
    target: { value: "John Doe" }
  });

  fireEvent.change(screen.getByPlaceholderText("Email"), {
    target: { value: "john.doe@example.com" }
  });

  fireEvent.change(screen.getByPlaceholderText("Contraseña"), {
    target: { value: "password123" }
  });

  fireEvent.change(screen.getByPlaceholderText("Teléfono"), {
    target: { value: "1234567890" }
  });

  fireEvent.change(screen.getByPlaceholderText("Dirección"), {
    target: { value: "123 Main St" }
  });

  fireEvent.click(
    screen.getByRole("button", { name: "Registrar" })
  );

    await waitFor(() => {

    expect(register).toHaveBeenCalledWith(
      "12345678-9",
      "John Doe",
      "john.doe@example.com",
      "password123",
      "1234567890",
      "123 Main St"
    );

    expect(localStorage.getItem("token"))
      .toBe("abc123");

    expect(alertMock)
      .toHaveBeenCalledWith("Registro exitoso");

    expect(mockNavigate)
      .toHaveBeenCalledWith("/login");
  });
});

