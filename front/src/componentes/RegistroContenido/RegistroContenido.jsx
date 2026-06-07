import './RegistroContenido.css';
import { useNavigate } from 'react-router-dom';

// Cambiamos a Mayúscula la primera letra
const RegistroContenido = () => {
    const navigate = useNavigate();

    return (
        <div className="contenedorBody">
            <h1>Registro de usuario</h1>
            <form className="registro-form">
                <div className='contenedorCP'>
                    <input type="text" placeholder="Rut" className="input-linea" />
                    <input type="text" placeholder="Nombre" className="input-linea" />
                    <input type="email" placeholder="Email" className="input-linea" />
                    <input type="password" placeholder="Contraseña" className="input-linea" />
                    <input type="text" placeholder="Teléfono" className="input-linea" />
                    <input type="text" placeholder="Dirección" className="input-linea" />
                </div>
                <div className="contenedorBoton">
                    <button type="submit" className="boton-registrar">Registrar</button>
                    <button type="button" className="boton-volver" onClick={() => navigate('/login')}>
                        Volver
                    </button>
                </div>
            </form>
        </div>
    );
};

export default RegistroContenido;
