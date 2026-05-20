import './HomePrincipal.css';
import { useNavigate } from 'react-router-dom';

const HomePrincipal = () => {
    const navigate = useNavigate();

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
                    <button onClick={() => navigate('/mascota')} className="btn-home">
                        Registrar Mascota
                    </button>
                    <button onClick={() => navigate('/reportar')} className="btn-home">
                        Reportar
                    </button>
                </div>
            </div>
        </div>
    );
}

export default HomePrincipal;