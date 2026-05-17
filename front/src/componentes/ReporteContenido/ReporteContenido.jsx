import './ReporteContenido.css';

const ReporteContenido = () => {

    const handleLimpiar = () => {
        // Lógica para resetear el formulario si es necesario
        document.getElementById("form-reporte").reset();
    };

    return (
        <div className="contenedor-reporte">
            <h1>Reportar pérdida de mascota</h1>
            
            <form id="form-reporte" className="form-estilo-visual">
                <div className='contenedor-inputs'>
                    <input type="text" placeholder="Nombre de la mascota registrada" className="input-bajo" />
                    <input type="text" placeholder="Última ubicación vista (Comuna/Calle)" className="input-bajo" />
                    <input type="text" placeholder="Fecha de desaparición" className="input-bajo" />
                    <input type="text" placeholder="Teléfono de contacto" className="input-bajo" />
                    <textarea placeholder="Descripción adicional (color de collar, señas particulares...)" className="input-bajo area-texto" />
                </div>

                <div className="contenedor-acciones">
                    <button type="submit" className="btn-informar">Informar</button>
                    <button type="button" className="btn-limpiar" onClick={handleLimpiar}>
                        Limpiar
                    </button>
                </div>
            </form>
        </div>
    );
}

export default ReporteContenido;