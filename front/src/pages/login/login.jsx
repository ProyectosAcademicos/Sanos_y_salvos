import HeaderGeneral from "../../componentes/header/headerGeneral/HeaderGeneral";
import FooterGeneral from "../../componentes/footer/footerGeneral/footerGeneral";

const Login = () => {
  return (
    <div>
        <HeaderGeneral />
        <h1>Login</h1>
        <form>
            <input type="email" placeholder="Email" />
            <input type="password" placeholder="Contraseña" />
            <button type="submit" onClick={"/login"}>Acceder</button>
            <button type="submit">Recuperar clave</button>
        </form>
        <FooterGeneral />
    </div>
  );
};

export default Login;