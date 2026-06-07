import Home from "./pages/home/home";
import Login from "./pages/login/login";
import Registro from "./pages/registro/registro";
import Mascota from "./pages/mascota/mascota";
import HomePrincipal from "./pages/homePrincipal/homePrincipal";
import Reporte from "./pages/reporte/reporte";
import Estado from "./pages/Estado/estado";
import Cerrar from "./pages/home/home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context//AuthContext/AuthContext";


function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* Rutas generales */}
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<HomePrincipal />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Registro />} />
          <Route path="/mascota" element={<Mascota />} />
          <Route path="/reportar" element={<Reporte />} />
          <Route path="/estado" element={<Estado />} />
          <Route path="/cerrar" element={<Cerrar />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;