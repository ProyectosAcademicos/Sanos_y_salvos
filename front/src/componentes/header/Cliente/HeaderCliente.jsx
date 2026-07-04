import './HeaderCliente.css';
import { useEffect, useState } from "react";
import { obtenerNotificaciones } from "../../../services/notificacionService";
import NotificacionesModal from "../../Notificaciones/NotificacionesModal";

const HeaderCliente = () => {

    const [menuOpen, setMenuOpen] = useState(false);
    const [notificaciones, setNotificaciones] = useState([]);
    const [openNoti, setOpenNoti] = useState(false);

    const rut = localStorage.getItem("rut");

    const cargarNotificaciones = async () => {
        try {
            const data = await obtenerNotificaciones(rut);
            setNotificaciones(data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        if (!rut) return;

        cargarNotificaciones();

        const interval = setInterval(() => {
            cargarNotificaciones();
        }, 10000); // cada 10 segundos

        return () => clearInterval(interval);
    }, [rut]);

    const noLeidas = notificaciones.filter(n => !n.leido).length;

    return (
        <header className="header-general">

            <img
                className="logo"
                src="https://res.cloudinary.com/dr84axabl/image/upload/v1777925023/logo_hwsz7k.png"
                alt="logo"
            />

            {/* CAMPANA */}
            <div className="noti-bell" onClick={() => setOpenNoti(true)}>
                🔔
                {noLeidas > 0 && (
                    <span className="badge">{noLeidas}</span>
                )}
            </div>

            {/* MENU */}
            <button onClick={() => setMenuOpen(!menuOpen)}>
                ☰
            </button>

            {menuOpen && (
                <div className="menu-mobile">
                    <a href="/">Home</a>
                    <a href="/mascota">Registrar Mascota</a>
                    <a href="/contacto">Contacto</a>
                </div>
            )}

            {/* MODAL */}
            {openNoti && (
                <NotificacionesModal
                    notificaciones={notificaciones}
                    onClose={() => setOpenNoti(false)}
                    onRefresh={cargarNotificaciones}
                />
            )}

        </header>
    );
};

export default HeaderCliente;