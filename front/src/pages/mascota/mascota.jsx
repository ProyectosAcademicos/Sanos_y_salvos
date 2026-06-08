import HeaderGeneral from "../../componentes/header/Cliente/HeaderCliente";
import FooterGeneral from "../../componentes/footer/footerGeneral/footerGeneral";
import RegistroMascota from "../../componentes/RegistroMascota/RegistroMascota";

const Mascota = () => {
    return(
        <div>
            <HeaderGeneral />
            <RegistroMascota />
            <FooterGeneral />
        </div>
    );
}

export default Mascota;

