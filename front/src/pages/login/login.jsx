import HeaderGeneral from "../../componentes/Header/HeaderGeneral/HeaderGeneral";
import FooterGeneral from "../../componentes/Footer/FooterGeneral/FooterGeneral";
import LoginContenido from "../../componentes/LoginContenido/LoginContenido";

const Login = () => {
  return (
    <div>
        <HeaderGeneral />
        <LoginContenido />
        <FooterGeneral />
    </div>
  );
};

export default Login;