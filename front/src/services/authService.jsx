

export async function login(rut, contrasena) {

  const response = await fetch("http://localhost:8081/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      rut,
      contrasena
    })
  });

  return response.json();
}