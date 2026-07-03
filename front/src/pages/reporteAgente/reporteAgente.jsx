import Header from "../../componentes/header/AgenteExterno/AgenteExterno";
import Footer from "../../componentes/Footer/footerGeneral/footerGeneral";
import ReporteAgenteContenido from "../../componentes/ReporteContenido/AgenteExterno/AgenteExterno";



const ReporteAgente = () => {
  return (
    <div>
        <Header />
        <ReporteAgenteContenido />
        <Footer />  
    </div>
  );
};

export default ReporteAgente;