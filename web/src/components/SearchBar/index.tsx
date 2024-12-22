import "./styles.scss";
import { IoSearch } from "react-icons/io5";

export default function SearchBar() {
  return (
    <div className="search-bar-container">
      <IoSearch />
      <input type="text" placeholder="Digite um nome ou raça desejada" />
    </div>
  );
}
