

export async function registrarMascota(nombre, tipo, raza, edad, tamano, descripcion) {

    if (
        nombre === "" ||
        tipo === "" ||
        raza === "" ||
        edad === "" ||
        tamano === "" ||
        descripcion === ""
    ) {
        alert("Por favor, complete todos los campos");
        return;
    }


    const response = await fetch("http://localhost:8081/api/mascotas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nombre,
            tipo,
            raza,
            edad,
            tamano,
            descripcion
        })
    });

    if (!response.ok) {
        let mensajeError = "Error al registrar mascota";

        try {
            const errorData = await response.json();
            mensajeError = errorData.message || mensajeError;
        } catch {
            mensajeError = "Error al registrar mascota";
        }
        throw new Error(mensajeError);
    }

    return response.json();
}
