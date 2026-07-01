import './HomeContenido.css';
import { useNavigate } from "react-router-dom";

const HomeContenido = () => {

    const navigate = useNavigate();

    return(
        <div className="flex flex-row items-center justify-center gap-10 px-8">
                <div>
                    <img 
                        className="portada m-4 object-cover ml-2 rounded-[20px]" 
                        src="https://res.cloudinary.com/dr84axabl/image/upload/v1777935003/foto_portada_mkm4wm.png" 
                        alt="portada" 
                    />
                </div>

                <div className="h-screen flex flex-col items-center justify-center p-4 gap-8">
                    <h1>Bienvenido a Sanos y Salvos</h1>
                    <div className="flex w-[300px] p-[19px_30px] justify-center items-center gap-2 rounded-[20px] border border-gray-300 bg-[#F5F7FA]">
                        <button onClick={() => navigate("/tipo-usuario")} className="btnIngresar">Ingresar</button>
                    </div>
                </div>
            </div>  
    )
}

export default HomeContenido;