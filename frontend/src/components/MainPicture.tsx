
import '../styles/MainPicture.css';
import Picture from '../assets/vegetables.png';

export default function MainPicture() {
    return (
        <div className="main-picture">
            <img src={Picture} alt="Vegetables" className="picture-overlay" />
        </div>
    );
}
