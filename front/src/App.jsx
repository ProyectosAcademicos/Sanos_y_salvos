import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import Estado from "./pages/Estado/estado";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext/AuthContext";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* Rutas generales */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          {/* Rutas protegidas */}
          <Route path="/estado" element={<Estado />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;