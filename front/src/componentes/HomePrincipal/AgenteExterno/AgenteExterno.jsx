import './AgenteExterno.css';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';




const HomeAgenteExterno = () => {
    const navigate = useNavigate();

    useEffect(() => {

        const rut = localStorage.getItem("rut");

        if (!rut) return;

        fetch(`http://localhost:8081/bff/dashboard/${rut}`)
            .then(response => response.json())
            .then(data => {
                console.log("Dashboard BFF:", data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    return (
        <div className="home-container">
            <h2 className="home-title">Home</h2>
            
            <div className="home-content">
                <div className="portada-wrapper">
                    <img 
                        className="portada-img" 
                        src="https://res.cloudinary.com/dr84axabl/image/upload/v1777935003/foto_portada_mkm4wm.png" 
                        alt="Familia con mascotas" 
                    />
                </div>

                <div className="botones-columna">
                    <button onClick={() => navigate('/reporte-externo')} className="btn-home">
                        Reportar
                    </button>
                </div>
            </div>
        </div>
    );
}

export default HomeAgenteExterno;