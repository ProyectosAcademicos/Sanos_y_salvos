export async function registrarReporte(
    rutUsuario,
    idMascota,
    ubicacionPerdida,
    fechaPerdida,
    descripcion
) {

    const body = {
        rutUsuario,
        idMascota,
        ubicacionPerdida,
        fechaPerdida,
        descripcion,
        estado: "Activo",
        tipoReporte: "perdida"
    };

    console.log("esto se manda al backend:", body);

    const response = await fetch(
        "http://localhost:8081/reportes",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        }
    );

    const texto = await response.text();

    if (!response.ok) {
        throw new Error("Error al registrar el reporte");
    }

    return JSON.parse(texto);
}

export async function registrarMascotaAgente(
    rutUsuario,
    idMascota,
    ubicacionPerdida,
    fechaPerdida,
    descripcion
) {

    const body = {
        rutUsuario,
        idMascota: "NN",
        ubicacionPerdida,
        fechaPerdida,
        descripcion,
        estado: "Activo",
        tipoReporte: "encontrada"
    };

    const response = await fetch("http://localhost:8081/reportes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });

    console.log(body)
    const texto = await response.text();
    console.log("Código:", response.status);
    console.log("Respuesta:", texto);

    if (!response.ok) {
        throw new Error("No fue posible registrar la mascota.");
    }

    return JSON.parse(texto);
}