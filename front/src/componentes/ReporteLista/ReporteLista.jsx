import { useEffect, useState } from "react";
import ReporteCard from "../ReporteCard/ReporteCard";

function ReporteLista() {

  const [mascotas, setMascotas] = useState([]);
  useEffect(() => {
    const fetchMascotas = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/mascotas"
        );

        if (!response.ok) {
          throw new Error("Error al obtener mascotas");
        }

        const data = await response.json();
        console.log(data);
        setMascotas(data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchMascotas();
  }, []);

  return (
    <div>
      {mascotas.map((mascota) => (
        <ReporteCard
            key={mascota.id}
            id={mascota.id}
            nombre={mascota.nombre}
            tipo={mascota.tipo}
            raza={mascota.raza}
            edad={mascota.edad}
            tamaño={mascota.tamaño}
            descripcion={mascota.descripcion}
            idUsuario={mascota.idUsuario}
            estado={mascota.estado}
        />
      ))}
    </div>
  );
}

export default ReporteLista;