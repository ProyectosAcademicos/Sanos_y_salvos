

export async function register(nombre, apellido, email, contrasena) {
  const response = await fetch("http://localhost:8081/auth/usuarios/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      nombre,
      apellido,
      email,
      contrasena
    })
  });

  if (nombre === "" || apellido === "" || email === "" || contrasena === "") {
    alert("Por favor, complete todos los campos");
    return;
  }

  if (!response.ok) {
    throw new Error("Error al registrar el usuario");
  }

  return response.json();

}

