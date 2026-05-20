import { useState } from "react";

function ReporteCard({ id, nombre, tipo, raza, edad, tamaño, descripcion, idUsuario, estado: estadoInicial }) {

    const [estado, setEstado] = useState(estadoInicial);
    const handleButtonClick = () => {
        setEstado("Encontrado");
        //HACER UN PATCH
        const updateEstado = async () => {
            try {
                const response = await fetch(
                    `http://localhost:8080/api/mascotas/${id}/estado`,
                    {
                        method: "PATCH",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify("Encontrado")
                    }
                );

                if (!response.ok) {
                    throw new Error("Error al actualizar estado");
                }

                const data = await response.json();
                console.log(data);
            } catch (error) {
                console.error("Error updating estado:", error);
            }
        };
        updateEstado();
    };


//     useEffect(() => {
//     const fetchEstado = async () => {
//       try {
//         const response = await fetch(
//           `http://localhost:8080/api/mascotas`
//         );

//         if (!response.ok) {
//           throw new Error("Error al obtener datos");
//         }

//         const data = await response.json();
//         console.log(data);
//       } catch (error) {
//         console.error("Error fetching estado:", error);
//       }
//     };
//     fetchEstado();
//   }, []); 


  return (
    <div className="reporte-card">
        <h3>{nombre}</h3>
        <p>Tipo: {tipo}</p>
        <p>Raza: {raza}</p>
        <p>Edad: {edad}</p>
        <p>Tamaño: {tamaño}</p>
        <p>Descripción: {descripcion}</p>
        <p>ID Usuario: {idUsuario}</p>
        <p>Estado: {estado}</p>
        <button onClick={handleButtonClick}>
            Cambiar estado
        </button>
    </div>
  );
}

export default ReporteCard;

