import HeanderGeneral from "../../componentes/header/headerGeneral/headerGeneral";
import FooterGeneral from "../../componentes/footer/footerGeneral/footerGeneral";
import TipoUsuarioContenido from "../../componentes/TipoUsuario/TipoUsuario";

const TipoUsuario = () => {
    return(
        <div>
            <HeanderGeneral />
            <TipoUsuarioContenido />
            <FooterGeneral />
        </div>
    );
};

export default TipoUsuario;