import "./footer.css";

const footerGeneral = () => {
    return (
        <div className="footerGeneral">
            <p>© 2024 Copyright:</p>
            <div className="social-media flex flex-row justify-space-beetween gap-4">
                <a href="https://www.facebook.com/" target="_blank" rel="noopener noreferrer">
                <img src="https://res.cloudinary.com/dr84axabl/image/upload/v1777938724/Vector_onf3hh.svg" alt="logo_fb" />
                </a>
                <a href="https://www.instagram.com/" target="_blank" rel="noopener noreferrer">
                    <img src="https://res.cloudinary.com/dr84axabl/image/upload/v1777938782/Vector_yhijwi.svg" alt="logo_ig" />
                </a>
            </div>
        </div>
    );
}

export default footerGeneral;