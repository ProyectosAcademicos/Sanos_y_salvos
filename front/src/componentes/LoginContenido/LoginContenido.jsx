import './LoginContenido.css';

import {  useState } from 'react';
// import { Link } from "react-router-dom";

import { login } from "../../services/authService";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";

const LoginContenido = () => {
    
    const { tipoUsuario } = useParams();
    const [rut, setRut] = useState("");
    const [contrasena, setContrasena] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const data = await login(rut, contrasena);
            localStorage.setItem("token", data.token);
            localStorage.setItem("rut", rut);
            alert("Inicio de sesión exitoso");
            console.log("LOGIN EJECUTADO");
            if (tipoUsuario === "agente_externo") {
                navigate("/home-agente-externo");
            } else {
                navigate(`/home-cliente/${tipoUsuario}`);
            }

        } catch (error) {
            alert("Error al iniciar sesión: Usuario no registrado o contraseña incorrecta");
            console.error("Error al iniciar sesión:", error);
        }
    }
    
    const nombresRoles = {
        administrador: "Administrador",
        Dueño_mascota: "Dueño Mascota",
        agente_externo: "Agente Externo"
    };

    const titulo = nombresRoles[tipoUsuario] || "Usuario";

    return(
        <div>
            <h1>Login {titulo}</h1>
            <form onSubmit={handleLogin}>
                <div className='contenedorLogin'>
                    <input 
                        className='input'
                        type="text" 
                        placeholder="Rut" 
                        value={rut}
                        onChange={(e) => setRut(e.target.value)}
                    />
                    <input 
                        className='input'
                        type="password" 
                        placeholder="Contraseña" 
                        value={contrasena}
                        onChange={(e) => setContrasena(e.target.value)}
                    />
                </div>
                <div className="contenedorBoton">
                    <div className="contenedorBotonesPrimarios">
                        <button className="botonPrimario" type="submit">Acceder</button>
                        <button onClick={() => navigate("/recuperar-clave")} className="botonSecundario" type="button">Recuperar clave</button>
                    </div>
                </div>
                <button
                    type="button"
                    className="botonRegistrarse"
                    onClick={() => {
                        navigate(`/registro/${tipoUsuario}`);
                    }}
                >
                    Registrarse
                </button>
            </form>
        </div>
        
    )
}

export default LoginContenido;