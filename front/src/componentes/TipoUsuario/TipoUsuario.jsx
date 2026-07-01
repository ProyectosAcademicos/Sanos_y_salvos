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
                    <option value="cliente">Cliente</option>
                    <option value="agente_externo">Agente Externo</option>
                    <option value="supervisor">Supervisor</option>
                    <option value="operador">Operador</option>
                </select>

                <div className="flex w-[300px] justify-center">
                    <button
                        onClick={ingresar}
                        className="btnIngresar"
                    >
                        Ingresar
                    </button>
                </div>

            </div>

        </div>
    );
};

export default TipoUsuario;