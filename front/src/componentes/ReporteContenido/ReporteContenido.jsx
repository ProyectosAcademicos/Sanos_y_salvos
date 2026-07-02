import "./ReporteContenido.css";
import { useEffect, useState } from "react";
import { registrarReporte } from "../../services/reporteService";
import { obtenerMascotas } from "../../services/mascotaService";
import { useNavigate } from "react-router-dom";

const ReporteContenido = () => {
  const [mascotas, setMascotas] = useState([]);
  const [idMascota, setIdMascota] = useState("");

  const [ubicacion, setUbicacion] = useState("");
  const [fecha, setFecha] = useState("");
  const [descripcion, setDescripcion] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    const cargarMascotas = async () => {
      try {
        const data = await obtenerMascotas();
        setMascotas(data);
      } catch (error) {
        console.error(error);
        alert("No fue posible cargar las mascotas.");
      }
    };

    cargarMascotas();
  }, []);
  

  const handleLimpiar = () => {
    setIdMascota("");
    setUbicacion("");
    setFecha("");
    setDescripcion("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await registrarReporte(
        "12345678-9",
        Number(idMascota),
        ubicacion,
        fecha,
        descripcion
      );

      alert("Reporte realizado correctamente");
      navigate("/home");
    } catch (error) {
      console.error(error);
      alert(error.message);
    }
  };

  return (
    <div className="contenedor-reporte">
      <h1>Reportar pérdida de mascota</h1>

      <form
        id="form-reporte"
        className="form-estilo-visual"
        onSubmit={handleSubmit}
      >
        <div className="contenedor-inputs">

          <select
            className="input-bajo"
            value={idMascota}
            onChange={(e) => setIdMascota(e.target.value)}
            required
          >
            <option value="">Seleccione una mascota</option>

            {mascotas.map((mascota) => (
              <option key={mascota.id} value={mascota.id}>
                {mascota.nombre}
              </option>
            ))}
          </select>

          <input
            type="text"
            placeholder="Última ubicación vista (Comuna/Calle)"
            className="input-bajo"
            value={ubicacion}
            onChange={(e) => setUbicacion(e.target.value)}
            required
          />

          <input
            type="date"
            className="input-bajo"
            value={fecha}
            onChange={(e) => setFecha(e.target.value)}
            required
          />

          <textarea
            placeholder="Descripción adicional (color de collar, señas particulares...)"
            className="input-bajo area-texto"
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            required
          />
        </div>

        <div className="contenedor-acciones">
          <div className="contenedor-acciones-secundarias">
            <button type="submit" className="btn-informar">
              Informar
            </button>

            <button
              type="button"
              className="btn-informar"
              onClick={handleLimpiar}
            >
              Limpiar
            </button>

            <button
              type="button"
              className="btn-informar"
              onClick={() => navigate("/home")}
            >
              Volver
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default ReporteContenido;

// import "./ReporteContenido.css";
// import { useState, useEffect } from "react";
// import { registrarReporte } from "../../services/reporteService";
// import { useNavigate } from "react-router-dom";
// import { obtenerMascotas } from "../../services/mascotaService";

// const ReporteContenido = () => {
//   const [mascotas, setMascotas] = useState([]);
//   const [idMascota, setIdMascota] = useState("");
//   const [ubicacion, setUbicacion] = useState("");
//   const [fecha, setFecha] = useState("");
//   const [descripcion, setDescripcion] = useState("");

//   const navigate = useNavigate();

//     useEffect(() => {
//       cargarMascotas();
//     }, []);

//   const cargarMascotas = async () => {
//     try {
//       const data = await obtenerMascotas();
//       setMascotas(data);
//     } catch (error) {
//       console.error(error);
//       alert("No fue posible cargar las mascotas.");
//     }
//   };


//   const handleLimpiar = () => {
//     setIdMascota("");
//     setUbicacion("");
//     setFecha("");
//     setDescripcion("");
//   };

// const handleSubmit = async (e) => {
//   e.preventDefault();

//   try {

//     await registrarReporte(
//       "12345678-9",
//       1,
//       ubicacion,
//       fecha,
//       descripcion
//     );

//     alert("Reporte realizado correctamente");
//     navigate("/home");

//   } catch (error) {
//     console.error(error);

//     alert(error.message);
//   }
// };

//   return (
//     <div className="contenedor-reporte">
//       <h1>Reportar pérdida de mascota</h1>

//       <form
//         id="form-reporte"
//         className="form-estilo-visual"
//         onSubmit={handleSubmit}
//       >
//         <div className="contenedor-inputs">
//           <select
//             className="input-bajo"
//             value={idMascota}
//             onChange={(e) => setIdMascota(e.target.value)}
//             required
//           >
//             <option value="">Seleccione una mascota</option>

//             {mascotas.map((mascota) => (
//               <option key={mascota.id} value={mascota.id}>
//                 {mascota.nombre}
//               </option>
//             ))}
//           </select>
//           <input
//             type="text"
//             placeholder="Última ubicación vista (Comuna/Calle)"
//             className="input-bajo"
//             value={ubicacion}
//             onChange={(e) => setUbicacion(e.target.value)}
//           />
//           <input
//             type="text"
//             placeholder="Fecha de desaparición"
//             className="input-bajo"
//             value={fecha}
//             onChange={(e) => setFecha(e.target.value)}
//           />
//           <textarea
//             placeholder="Descripción adicional (color de collar, señas particulares...)"
//             className="input-bajo area-texto"
//             value={descripcion}
//             onChange={(e) => setDescripcion(e.target.value)}
//           />
//         </div>
//         <div className="contenedor-acciones">
//           <div className="contenedor-acciones-secundarias">
//             <button type="submit" className="btn-informar">
//               Informar
//             </button>
//             <button
//               type="button"
//               className="btn-informar"
//               onClick={handleLimpiar}
//             >
//               Limpiar
//             </button>

//             <button
//               type="button"
//               className="btn-informar"
//               onClick={() => navigate("/home")}
//             >
//               Volver
//             </button>
//           </div>
//         </div>
//       </form>
//     </div>
//   );
// };

// export default ReporteContenido;
