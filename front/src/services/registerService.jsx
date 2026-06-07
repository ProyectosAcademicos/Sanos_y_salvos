

export async function register(rut, nombre, email, contrasena, telefono, direccion) {

    if (
        rut === "" ||
        nombre === "" ||
        email === "" ||
        contrasena === "" ||
        telefono === "" ||
        direccion === ""
        ) {
        alert("Por favor, complete todos los campos");
        return;
    }   


    const response = await fetch("http://localhost:8081/auth/usuarios/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      rut,
      nombre,
      email,
      contrasena,
      telefono,
      direccion,
      tipoUsuario: "DUEÑO"
    })
  });



  //si la respuesta un error, se intenta parsear el mensaje de error del backend, si no se puede, se muestra un mensaje genérico
    if (!response.ok) {

        let mensajeError = "Error al registrar usuario";

        try{
            const errorData = await response.json();
            mensajeError = errorData.message || mensajeError;
        } catch {
            // Si no se puede parsear el JSON, se mantiene el mensaje de error genérico
            mensajeError = "Error al registrar usuario";
        }
        throw new Error(mensajeError);
    }

  return response.json();

}

