import NavBar from "../../components/NavBar";
import "./styles.scss";
import LoginImage from "../../assets/Login-image.png";
import { FaLocationDot } from "react-icons/fa6";
import { MdOutlinePets } from "react-icons/md";
import { FaBirthdayCake } from "react-icons/fa";
import { useLocation } from "react-router-dom";

export default function PetDetail() {
  const location = useLocation();

  return (
    <div className="pet-detail-container">
      <NavBar />

      <div className="content">
        <div className="name-button">
          <span className="name">{location.state.name}</span>
          <button>Adotar</button>
        </div>

        <div className="user-address">
          <div className="username">
            <img src={LoginImage} />
            <span>John Doe</span>
          </div>
          <span className="address">
            <FaLocationDot />
            {location.state.address}
          </span>
        </div>

        <span className="breed">
          <MdOutlinePets />
          {location.state.breed}
        </span>
        <span className="age">
          <FaBirthdayCake /> {location.state.age}
        </span>
        <div className="image-pet">
          <img src={location.state.image} />
        </div>
      </div>
    </div>
  );
}
