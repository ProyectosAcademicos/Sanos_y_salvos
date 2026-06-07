

export async function login(rut, contrasena) {

  console.log("ENVIANDO:");
  console.log("rut =", rut);
  console.log("contrasena =", contrasena);
  
  const response = await fetch("http://localhost:8081/auth/usuarios/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      rut,
      contrasena
    })
  });
  console.log("rut:", response.rut);
  console.log("contrasena:", response.contrasena);

  console.log("STATUS:", response.status);

  if (!response.ok) {
    throw new Error("Credenciales incorrectas");
  }


  return response.json();
}