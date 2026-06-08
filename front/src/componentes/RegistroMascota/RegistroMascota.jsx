import './RegistroMascota.css'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { registrarMascota } from '../../services/mascotaService';

// Formulario de registro de mascota
const RegistroMascota = () => {

    const [nombre, setNombre] = useState("");
    const [tipo, setTipo] = useState("");
    const [raza, setRaza] = useState("");
    const [edad, setEdad] = useState("");
    const [tamaño, setTamaño] = useState("");
    const [descripcion, setDescripcion] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async(e) => {
        e.preventDefault();
       try{
        await registrarMascota(nombre, tipo, raza, edad, tamaño, descripcion);
        alert("Mascota registrada exitosamente");
        navigate('/home-cliente');
       } catch (error) {
        alert("Error al registrar mascota: " + error.message);
        console.error("Error al registrar mascota:", error);
       }
    }
    
    return (
        <div className="contenedorBody">
            <h1>Registro de mascota</h1>
            <form className="registro-form" onSubmit={handleSubmit}>
                <div className='contenedorCP'>
                    <input 
                        type="text" 
                        placeholder="Nombre de la mascota" 
                        className="input-linea" 
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                    />
                    <input 
                        type="text" 
                        placeholder="Tipo" 
                        className="input-linea" 
                        value={tipo}
                        onChange={(e) => setTipo(e.target.value)}
                    />
                    <input 
                        type="text" 
                        placeholder="Raza" 
                        className="input-linea" 
                        value={raza}
                        onChange={(e) => setRaza(e.target.value)}
                    />
                    <input 
                        type="number" 
                        placeholder="Edad" 
                        className="input-linea" 
                        value={edad}
                        onChange={(e) => setEdad(e.target.value)}
                    />
                    <input 
                        type="text" 
                        placeholder="Tamaño" 
                        className="input-linea" 
                        value={tamaño}
                        onChange={(e) => setTamaño(e.target.value)}
                    />
                    <input 
                        type="text" 
                        placeholder="Descripción" 
                        className="input-linea" 
                        value={descripcion}
                        onChange={(e) => setDescripcion(e.target.value)}
                    />
                </div>
                <div className="contenedorBoton">
                    <button type="submit" className="boton-registrar">Registrar</button>
                    <button type="button" className="boton-volver" onClick={() => navigate('/home-cliente')}>
                        Volver
                    </button>
                </div>
            </form>
        </div>
    );
}

export default RegistroMascota;