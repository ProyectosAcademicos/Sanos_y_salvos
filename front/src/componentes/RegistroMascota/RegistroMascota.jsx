import './RegistroMascota.css'
import { useNavigate } from 'react-router-dom';

// Formulario de registro de mascota
const RegistroMascota = () => {
    const navigate = useNavigate();
    
    return (
        <div className="contenedorBody">
            <h1>Registro de mascota</h1>
            <form className="registro-form">
                <div className='contenedorCP'>
                    <input type="text" placeholder="Nombre de la mascota" className="input-linea" />
                    <input type="text" placeholder="Tipo" className="input-linea" />
                    <input type="text" placeholder="Raza" className="input-linea" />
                    <input type="number" placeholder="Edad" className="input-linea" />
                    <input type="text" placeholder="Tamaño" className="input-linea" />
                    <input type="text" placeholder="Descripción" className="input-linea" />
                </div>
                <div className="contenedorBoton">
                    <button type="submit" className="boton-registrar">Registrar</button>
                    <button type="button" className="boton-volver" onClick={() => navigate('/home')}>
                        Volver
                    </button>
                </div>
            </form>
        </div>
    );
}

export default RegistroMascota;