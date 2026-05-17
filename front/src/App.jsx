import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import Registro from "./pages/Registro/Registro";
import Mascota from "./pages/Mascota/Mascota";
import HomePrincipal from "./pages/homePrincipal/homePrincipal";
import Reporte from "./pages/Reporte/Reporte";
import Estado from "./pages/Estado/estado";
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
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;