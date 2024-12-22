import Card from "../../components/Card";
import NavBar from "../../components/NavBar";
import SearchBar from "../../components/SearchBar";
import Tag from "../../components/Tag";
import "./styles.scss";
import { BiSolidDog } from "react-icons/bi";
import { FaCat } from "react-icons/fa6";
import { HiDotsHorizontal } from "react-icons/hi";

export default function Home() {
  return (
    <div className="home-container">
      <NavBar />
      <SearchBar />
      <div className="tags">
        <Tag Icon={BiSolidDog} label="Cachorro" />
        <Tag Icon={FaCat} label="Gato" />
        <Tag Icon={HiDotsHorizontal} label="Outros" />
      </div>
      <div className="cards">
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
        <Card
          image="Image"
          name="Toby Allen"
          address="Wynewood, 23"
          breed="Boxer"
          age="2 years"
        />
      </div>
    </div>
  );
}
