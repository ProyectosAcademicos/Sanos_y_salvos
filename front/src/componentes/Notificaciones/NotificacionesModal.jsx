import { marcarComoLeida } from "../../services/notificacionService";

const NotificacionesModal = ({ notificaciones, onClose, onRefresh }) => {

    const handleLeida = async (id) => {
        try {
            await marcarComoLeida(id);
            onRefresh();
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-box">

                <div className="modal-header">
                    <h2>🔔 Notificaciones</h2>
                    <button onClick={onClose}>X</button>
                </div>

                <div className="modal-body">
                    {notificaciones.length === 0 ? (
                        <p>No tienes notificaciones</p>
                    ) : (
                        notificaciones.map((n) => (
                            <div
                                key={n.id}
                                className={`noti ${n.leido ? "leida" : "noleida"}`}
                            >
                                <p>{n.mensaje}</p>
                                <small>
                                    {new Date(n.fechaEnvio).toLocaleString()}
                                </small>

                                {!n.leido && (
                                    <button onClick={() => handleLeida(n.id)}>
                                        Marcar como leída
                                    </button>
                                )}
                            </div>
                        ))
                    )}
                </div>

            </div>
        </div>
    );
};

export default NotificacionesModal;