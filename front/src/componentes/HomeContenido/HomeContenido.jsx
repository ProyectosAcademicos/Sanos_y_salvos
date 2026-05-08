

const HomeContenido = () => {
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
                    <h2>Bienvenido a Sanos y Salvos</h2>
                    <div className="contenedorBoton">
                        <button onClick={() => window.location.href = '/login'} className="btnIngresar">Ingresar</button>
                    </div>
                    
                </div>
            </div>  
    )
}

export default HomeContenido;