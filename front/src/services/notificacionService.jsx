export async function obtenerNotificaciones(rutUsuario) {
    const token = localStorage.getItem("token");

    const response = await fetch(
        `http://localhost:8081/api/notificaciones/usuario/${rutUsuario}`,
        {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
    );

    if (!response.ok) {
        throw new Error("Error al obtener notificaciones");
    }

    return response.json();
}

export async function marcarComoLeida(id) {
    const token = localStorage.getItem("token");

    const response = await fetch(
        `http://localhost:8081/api/notificaciones/${id}/leida`,
        {
            method: "PATCH",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
    );

    if (!response.ok) {
        throw new Error("Error al marcar notificación");
    }

    return response.json();
}