import "./styles.scss";
import { FaLocationDot } from "react-icons/fa6";
import { MdOutlinePets } from "react-icons/md";
import { FaBirthdayCake } from "react-icons/fa";
import LoginImage from "../../assets/Login-image.png";

type Props = {
  image: string;
  name: string;
  address: string;
  breed: string;
  age: string;
};

export default function Card({ image, name, address, breed, age }: Props) {
  return (
    <div className="card-container">
      <div className="avatar">
        <img src={LoginImage} alt={image} />
      </div>
      <span className="name">{name}</span>
      <span className="address">
        <FaLocationDot />
        {address}
      </span>
      <div className="breed-age">
        <span className="breed">
          <MdOutlinePets />
          {breed}
        </span>
        <span className="age">
          <FaBirthdayCake />
          {age}
        </span>
      </div>
    </div>
  );
}
