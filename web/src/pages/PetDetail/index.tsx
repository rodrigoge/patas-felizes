import NavBar from "../../components/NavBar";
import "./styles.scss";
import LoginImage from "../../assets/Login-image.png";
import { FaLocationDot } from "react-icons/fa6";
import { MdOutlinePets } from "react-icons/md";
import { FaBirthdayCake } from "react-icons/fa";

export default function PetDetail() {
  return (
    <div className="pet-detail-container">
      <NavBar />

      <div className="content">
        <div className="name-button">
          <span className="name">Caramelo</span>
          <button>Adotar</button>
        </div>

        <div className="user-address">
          <div className="username">
            <img src={LoginImage} />
            <span>John Doe</span>
          </div>
          <span className="address">
            <FaLocationDot />
            Rua St. Pierre, 50
          </span>
        </div>

        <span className="breed">
          <MdOutlinePets />
          Border Collie
        </span>
        <span className="age">
          <FaBirthdayCake /> 2 anos
        </span>
        <div className="image-pet">
          <img src={LoginImage} />
        </div>
      </div>
    </div>
  );
}
