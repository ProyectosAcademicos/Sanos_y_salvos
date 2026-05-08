

const loginContenido = () => {
    return(
        <div>
            <h1>Login</h1>
            <form>
                <input type="email" placeholder="Email" />
                <input type="password" placeholder="Contraseña" />
                <button type="submit">Acceder</button>
                <button type="submit">Recuperar clave</button>
            </form>
        </div>
        
    )
}

export default loginContenido;