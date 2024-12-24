import "./styles.scss";
import { FaLocationDot } from "react-icons/fa6";
import { MdOutlinePets } from "react-icons/md";
import { FaBirthdayCake } from "react-icons/fa";
import { FaRegImage } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

type Props = {
  image: string;
  name: string;
  address: string;
  breed: string;
  age: number;
};

export default function Card({ image, name, address, breed, age }: Props) {
  const navigate = useNavigate();

  async function handleOpenDetail() {
    navigate("/pet-detail", { state: { image, name, address, breed, age } });
  }

  return (
    <div className="card-container" onClick={() => handleOpenDetail()}>
      <div className="avatar">
        {image ? <img src={image} /> : <FaRegImage size={200} color="grey" />}
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
