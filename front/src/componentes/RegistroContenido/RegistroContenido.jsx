import './RegistroContenido.css';
import { useNavigate } from 'react-router-dom';
import { register } from "../../services/registerService";
import { useState } from 'react';
import { useParams } from "react-router-dom";

// Cambiamos a Mayúscula la primera letra
const RegistroContenido = () => {

    const [rut, setRut] = useState("");
    const [nombre, setNombre] = useState("");
    const [email, setEmail] = useState("");
    const [contrasena, setContrasena] = useState("");
    const [telefono, setTelefono] = useState("");
    const [direccion, setDireccion] = useState("");

    const navigate = useNavigate();
    const { tipoUsuario } = useParams();

    

    const handleRegistro = async (e) => {
        e.preventDefault();
        try {
            const data = await register(
                rut,
                nombre,
                email,
                contrasena,
                telefono,
                direccion,
                tipoUsuario
            );
            localStorage.setItem("token", data.token);
            alert("Registro exitoso");
            console.log("REGISTRO EJECUTADO");
            navigate(`/login/${tipoUsuario}`);
        } catch (error) {
            console.error('Error al registrar usuario:', error);
        }
    };

    const nombresRoles = {
        administrador: "Administrador",
        Dueño_mascota: "Dueño Mascota",
        agente_externo: "Agente Externo"
    };

    const nombreRole = nombresRoles[tipoUsuario] || tipoUsuario;

    return (
        <div className="contenedorForm">
            <h1>Registro de {nombreRole     }</h1>
            <form className="registro-form" onSubmit={handleRegistro}>
                <div className="contenedorDatos">
                    <input type="text" placeholder="Rut" className="input-linea" value={rut} onChange={(e) => setRut(e.target.value)} />
                    <input type="text" placeholder="Nombre" className="input-linea" value={nombre} onChange={(e) => setNombre(e.target.value)} />
                    <input type="email" placeholder="Email" className="input-linea" value={email} onChange={(e) => setEmail(e.target.value)} />
                    <input type="password" placeholder="Contraseña" className="input-linea" value={contrasena} onChange={(e) => setContrasena(e.target.value)} />
                    <input type="text" placeholder="Teléfono" className="input-linea" value={telefono} onChange={(e) => setTelefono(e.target.value)} />
                    <input type="text" placeholder="Dirección" className="input-linea" value={direccion} onChange={(e) => setDireccion(e.target.value)} />
                </div>
                <div className="contenedorBoton">
                    <button type="submit" className="boton-registrar">Registrar</button>
                    <button type="button" className="boton-volver" onClick={() => navigate(`/login/${tipoUsuario}`)}>
                        Volver
                    </button>
                </div>
            </form>
        </div>
    );
};

export default RegistroContenido;
