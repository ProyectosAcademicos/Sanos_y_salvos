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
        estado: "Activo"
    };

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