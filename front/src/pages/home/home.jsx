import Header from "../../componentes/Header/HeaderGeneral/HeaderGeneral";
import Footer from "../../componentes/Footer/FooterGeneral/FooterGeneral";
import HomeContenido from "../../componentes/HomeContenido/HomeContenido";
import './home.css';

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