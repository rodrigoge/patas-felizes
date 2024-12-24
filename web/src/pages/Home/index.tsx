import Card from "../../components/Card";
import NavBar from "../../components/NavBar";
import SearchBar from "../../components/SearchBar";
import Tag from "../../components/Tag";
import "./styles.scss";
import { BiSolidDog } from "react-icons/bi";
import { FaCat } from "react-icons/fa6";
import { HiDotsHorizontal } from "react-icons/hi";
import api from "../../services/api";
import { useEffect, useState } from "react";
import { PetInterface } from "../../interfaces/PetInterface";

enum PetType {
  DOG = "DOG",
  CAT = "CAT",
  OTHERS = "OTHERS",
}

type Params = {
  name?: string;
  type?: PetType;
  breed?: string;
  giverId?: string;
};

export default function Home() {
  const token = localStorage.getItem("token");
  const [pets, setPets] = useState<PetInterface[]>([]);

  useEffect(() => {
    handleGetPets();
  }, []);

  async function handleGetPets(params?: Params) {
    try {
      api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      const response = await api.get("/pets", {
        params: {
          type: params?.type,
        },
      });
      if (response) {
        setPets(response.data);
      }
    } catch (error) {
      return error;
    }
  }

  return (
    <div className="home-container">
      <NavBar />
      <SearchBar />
      <div className="tags">
        <Tag
          Icon={BiSolidDog}
          label="Cachorro"
          onClick={() => handleGetPets({ type: PetType.DOG })}
        />
        <Tag
          Icon={FaCat}
          label="Gato"
          onClick={() => handleGetPets({ type: PetType.CAT })}
        />
        <Tag
          Icon={HiDotsHorizontal}
          label="Outros"
          onClick={() => handleGetPets({ type: PetType.OTHERS })}
        />
      </div>
      <div className="cards">
        {pets.map((pet, key) => (
          <div key={key}>
            <Card
              image={pet.avatar}
              name={pet.name}
              address={pet.address}
              breed={pet.breed}
              age={pet.age}
            />
          </div>
        ))}
      </div>
    </div>
  );
}
