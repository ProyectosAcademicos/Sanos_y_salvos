import './LoginContenido.css';

import { useState } from 'react';

import { login } from "../../services/authService";
import { useNavigate } from "react-router-dom";

const LoginContenido = () => {

    const [rut, setRut] = useState("");
    const [contrasena, setContrasena] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const data = await login(rut, contrasena);
            localStorage.setItem("token", data.token);
            alert("Inicio de sesión exitoso");
            navigate("/home");

        } catch (error) {
            alert("Error al iniciar sesión: Usuario no registrado o contraseña incorrecta");
            console.error("Error al iniciar sesión:", error);
        }
    }

    return(
        <div>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <div className='contenedorCP'>
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
                        <button className="botonSecundario" type="submit">Recuperar clave</button>
                    </div>
                    <button className="botonRegistrarse" type="submit">Registrarse</button>
                </div>
            </form>
        </div>
        
    )
}

export default LoginContenido;