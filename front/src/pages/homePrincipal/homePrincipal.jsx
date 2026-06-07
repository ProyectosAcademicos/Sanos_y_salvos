import Header from "../../componentes/header/General/headerGeneral";
import Footer from "../../componentes/footer/footerGeneral/footerGeneral";
import HomePrincipal from "../../componentes/HomePrincipal/Cliente/HomePrincipal";

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