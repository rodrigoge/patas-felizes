import "./styles.scss";
import { IoSearch } from "react-icons/io5";

type Props = {
  name: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
};

export default function SearchBar({ name, onChange }: Props) {
  return (
    <div className="search-bar-container">
      <IoSearch />
      <input
        value={name}
        onChange={onChange}
        type="text"
        placeholder="Digite um nome ou raça desejada"
      />
    </div>
  );
}
