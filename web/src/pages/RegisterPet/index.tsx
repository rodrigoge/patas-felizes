import { Link } from "react-router-dom";
import NavBar from "../../components/NavBar";
import "./styles.scss";
import { FaArrowLeftLong } from "react-icons/fa6";
import { IoCloudUploadOutline } from "react-icons/io5";

export default function RegisterPet() {
  const handleOpenFileUpload = () => {
    const element = document.querySelector(".input-field");
    if (element instanceof HTMLElement) {
      element.click();
    } else {
      console.log("Error");
    }
  };

  return (
    <div className="register-pet-container">
      <NavBar />

      <form action="" className="form-container">
        <header>
          <h3>Inscreva um pet para ser adotado</h3>

          <Link to={"/home"} className="back-button">
            <FaArrowLeftLong />
            <span>Voltar</span>
          </Link>
        </header>

        <div className="input-container">
          <input type="text" placeholder="Digite o nome completo" />
        </div>

        <div className="input-container">
          <input type="text" placeholder="Digite o endereço completo" />
        </div>

        <div className="type-age-container">
          <div className="input-container">
            <input type="text" placeholder="Escolha o tipo de pet" />
          </div>
          <div className="input-age-container">
            <input type="text" placeholder="Digite a idade" />
          </div>
        </div>

        <div className="input-container">
          <input type="text" placeholder="Digite a raça" />
        </div>

        {/* <div className="input-file-container">
          <input type="file" placeholder="Imagem" />
        </div> */}

        <form
          action=""
          className="input-file-container"
          onClick={() => handleOpenFileUpload()}
        >
          <input type="file" accept="image/*" hidden className="input-field" />
          <IoCloudUploadOutline size={30} />
          <h4>Clique e selecione uma imagem para ser carregada</h4>
          <span>JPG, JPEG, PNG (Máximo 5MB)</span>
        </form>
        <button>Inscrever</button>
      </form>
    </div>
  );
}
