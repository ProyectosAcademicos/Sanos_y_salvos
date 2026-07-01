import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./TipoUsuario.css";

const TipoUsuario = () => {

    const [tipoUsuario, setTipoUsuario] = useState("");
    const navigate = useNavigate();

    const ingresar = () => {

        if (!tipoUsuario) {
            alert("Seleccione un tipo de usuario");
            return;
        }
        console.log("TIPO DE USUARIO SELECCIONADO:", tipoUsuario)

        navigate(`/login/${tipoUsuario}`);
    };

    return (
        <div className="flex flex-row items-center justify-center gap-10 px-8">

            <div className="h-screen flex flex-col items-center justify-center p-4 gap-8">

                <h1>Ingresa a Sanos y Salvos</h1>

                <select
                    value={tipoUsuario}
                    onChange={(e) => setTipoUsuario(e.target.value)}
                    className="w-[300px] p-4 rounded-[20px] border border-gray-300 bg-[#F5F7FA] outline-none"
                >
                    <option value="">Seleccione un tipo de usuario</option>
                    <option value="administrador">Administrador</option>
                    <option value="dueño de mascota">Dueño de mascota</option>
                    <option value="agente_externo">Agente Externo</option>
                </select>

                <div className="flex flex-col w-[300px] justify-center gap-6 border border-gray-300">
                    <button
                        onClick={ingresar}
                        className="btnIngresar bg-[#F5F7FA] p-4 rounded-[20px] font-bold text-[#1A1A1A] hover:bg-[#E0E0E0] transition-colors duration-300"
                    >
                        Ingresar
                    </button>
                    <button
                        onClick={() => navigate(`/login`)}
                        className="btnIngresar bg-[#F5F7FA] p-4 rounded-[20px] font-bold text-[#1A1A1A] hover:bg-[#E0E0E0] transition-colors duration-300   "
                        
                    >
                        Volver
                    </button>
                </div>
        

            </div>

        </div>
    );
};

export default TipoUsuario;