import './LoginContenido.css';

const loginContenido = () => {
    return(
        <div>
            <h1>Login</h1>
            <form>
                <div className='contenedorCP'>
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Contraseña" />
                </div>
                <div className="contenedorBoton">
                    <button type="submit">Acceder</button>
                    <button type="submit">Recuperar clave</button>
                </div>
                
            </form>
        </div>
        
    )
}

export default loginContenido;