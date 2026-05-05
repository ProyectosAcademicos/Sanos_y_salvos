import Header from "../../componentes/header/headerGeneral/HeaderGeneral";
import Footer from "../../componentes/footer/footerGeneral/footerGeneral";
import './home.css';

const Home = () => {
    return (
        <div className="">
            <Header />  
            <div className="flex flex-row items-center justify-center gap-10 px-8">
                
                <div>
                    <img 
                        className="portada m-4 object-cover ml-2 rounded-[20px]" 
                        src="https://res.cloudinary.com/dr84axabl/image/upload/v1777935003/foto_portada_mkm4wm.png" 
                        alt="portada" 
                    />
                </div>

                <div className="h-screen flex flex-col items-center justify-center p-4 gap-8">
                    <h2>Bienvenido a Sanos y Salvos</h2>
                    <div class="contenedorBoton">
                        <button class="btnIngresar">Ingresar</button>
                    </div>
                    
                </div>

            </div>  
            {/* <div className="flex flex-row items-center justify-center gap-10 px-8">
                <div className="">
                    <img className="portada m-4 object-cover ml-2" src="https://res.cloudinary.com/dr84axabl/image/upload/v1777935003/foto_portada_mkm4wm.png" alt="portada" />
                </div>
                <div className="h-screen flex flex-col items-center justify-center">
                    <h2>Bienvenido a nuestro sitio web</h2>
                    <button>Ingresar</button>
                </div>
            </div>             */}
            <Footer />
        </div>
    );
}

export default Home;