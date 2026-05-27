import HeaderGeneral from "../../componentes/header/headerGeneral/headerGeneral";
import FooterGeneral from "../../componentes/footer/footerGeneral/footerGeneral";
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