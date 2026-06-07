

export async function register(rut, nombre, email, contrasena, telefono, direccion) {


    console.log("ENVIANDO:");
    console.log("rut =", rut);
    console.log("contrasena =", contrasena);
    console.log("telefono =", telefono);
    console.log("direccion =", direccion);

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
      direccion
    })
  });

  if (rut === "" || nombre === "" || email === "" || contrasena === "" || telefono === "" || direccion === "") {
    alert("Por favor, complete todos los campos");
    return;
  }

  //si la respuesta es 403, mostrar un mensaje de error indicando que el usuario ya existe
    if (response.status === 403) {
        alert("Error al registrar usuario: El usuario ya existe");
        throw new Error("Error al registrar usuario: El usuario ya existe");
    }

    //si aparece otro tipo de error diferente a 403, mostrar un mensaje de error indicando el status del error
  if (!response.status !== 403) {
    alert("Error status: " + response.statusText + " al registrar usuario. Por favor, intente nuevamente.");
    throw new Error("Error status: " + response.statusText);
  }

  return response.json();

}

