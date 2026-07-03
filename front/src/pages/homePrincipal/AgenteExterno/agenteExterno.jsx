import Header from "../../../componentes/header/Cliente/HeaderCliente";
import Footer from "../../../componentes/footer/footerGeneral/footerGeneral";
import HomePrincipal from "../../../componentes/HomePrincipal/AgenteExterno/AgenteExterno";

const Home = () => {
    return (
        <div className="">
            <Header />  
            <HomePrincipal />
            <Footer />
        </div>
    );
}

export default Home;