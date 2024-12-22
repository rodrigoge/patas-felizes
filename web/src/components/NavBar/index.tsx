import "./styles.scss";
import Icon from "../../assets/favicon.svg";
import { Link } from "react-router-dom";

export default function NavBar() {
  return (
    <header className="navbar-container">
      <div className="logo-mark">
        <img src={Icon} />
        <span>Patas Felizes</span>
      </div>

      <div className="navigation">
        <Link to="">Adoções</Link>
        <Link to="">Doar</Link>
        <Link to="">Minha Conta</Link>
        <Link to="">Sair</Link>
      </div>
    </header>
  );
}
