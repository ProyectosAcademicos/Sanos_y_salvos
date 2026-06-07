import Header from "../../componentes/header/General/headerGeneral";
import Footer from "../../componentes/footer/footerGeneral/footerGeneral";
import HomeContenido from "../../componentes/HomeContenido/HomeContenido";
// import './home.css';

const Home = () => {
    return (
        <div className="">
            <Header />  
            <HomeContenido />
            <Footer />
        </div>
    );
}

export default Home;