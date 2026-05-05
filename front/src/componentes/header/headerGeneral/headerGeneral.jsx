import '../headerGeneral/HeaderGeneral.css';
import { useState } from "react";

const HeaderGeneral = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    return (
        <header className="header-general">
            {/* Logo de la empresa */}
            <img className="logo" src="https://res.cloudinary.com/dr84axabl/image/upload/v1777925023/logo_hwsz7k.png" alt="logo" />
            <button 
                onClick={() => setMenuOpen(!menuOpen)}
                className="md:hidden flex flex-col gap-1"
            >
                <span className="w-6 h-0.5 bg-black"></span>
                <span className="w-6 h-0.5 bg-black"></span>
                <span className="w-6 h-0.5 bg-black"></span>
            </button>

            {/* Menú desktop */}
            <div className="hidden md:block">
                <nav>
                    <ul className="flex gap-6">
                        <li><a href="/">Home</a></li>
                        <li><a href="/">Iniciar sesión</a></li>
                        <li><a href="/about">Reportar</a></li>
                        <li><a href="/contact">Contacto</a></li>
                    </ul>
                </nav>
            </div>

            {/* Menú mobile (hamburguesa) */}
            {menuOpen && (
                <div className="absolute top-0 left-0 w-full h-screen bg-white shadow-md md:hidden">
                    {/* Botón de cierre */}
                    <button 
                        onClick={() => setMenuOpen(false)}
                        className="absolute top-5 right-5 text-2xl font-bold hover:text-red-500 transition-colors"
                    >X</button>
                    <nav className="h-full">
                        <ul className="flex flex-col justify-center items-end h-full gap-4 pr-6">
                            <li><a href="/">Home</a></li>
                            <li><a href="/">Iniciar sesión</a></li>
                            <li><a href="/about">Reportar</a></li>
                            <li><a href="/contact">Contacto</a></li>
                        </ul>
                    </nav>
                </div>
            )}
        </header>
    );
};

export default HeaderGeneral;