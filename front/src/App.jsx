import Bienvenida from "./pages/Bienvenida/home";
import HomeCliente from "./pages/homePrincipal/Cliente/homePrincipal";
import Login from "./pages/login/login";
import Registro from "./pages/registro/registro";
import Mascota from "./pages/mascota/mascota";
import HomePrincipal from "./pages/homePrincipal/Cliente/homePrincipal";
import Reporte from "./pages/reporte/reporte";
import Estado from "./pages/Estado/estado";
import TipoUsuario from "./componentes/TipoUsuario/TipoUsuario";
import HomeAgenteExterno from "./pages/homePrincipal/AgenteExterno/agenteExterno";
import ReporteContenidoAgente from "./pages/reporte/reporteExterno/reporteExtrerno";
import Cerrar from "./pages/Bienvenida/home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context//AuthContext/AuthContext";


function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* Rutas generales */}
          <Route path="/" element={<Bienvenida />} />
          <Route path="/home" element={<HomePrincipal />} />
          <Route path="/home-cliente/:tipoUsuario" element={<HomeCliente />} />
          {/* <Route path="/login" element={<Login />} /> */}
          <Route path="/registro/:tipoUsuario" element={<Registro />} />
          <Route path="/mascota" element={<Mascota />} />
          <Route path="/reportar" element={<Reporte />} />
          <Route path="/estado" element={<Estado />} />
          <Route path="/tipo-usuario" element={<TipoUsuario />} />
          <Route path="/login/:tipoUsuario" element={<Login />} />
          <Route path="/home-agente-externo" element={<HomeAgenteExterno />} />
          <Route path="/reporte-externo" element={<ReporteContenidoAgente />} />
          <Route path="/cerrar" element={<Cerrar />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;