import { Link } from "react-router-dom";
import NavBar from "../../components/NavBar";
import "./styles.scss";
import { FaArrowLeftLong } from "react-icons/fa6";
import { IoCloudUploadOutline } from "react-icons/io5";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { useState } from "react";

export default function MyAccount() {
  const [password, setPassword] = useState<string>();
  const [hidePassword, setHidePassword] = useState(true);

  async function handleShowOrHidePassword() {
    const passwordInputElement = document.getElementById(
      "input-password"
    ) as HTMLInputElement;

    if (passwordInputElement.type === "password") {
      passwordInputElement.type = "text";
      setHidePassword(false);
    } else {
      passwordInputElement.type = "password";
      setHidePassword(true);
    }
  }

  const handleOpenFileUpload = () => {
    const element = document.querySelector(".input-field");
    if (element instanceof HTMLElement) {
      element.click();
    } else {
      console.log("Error");
    }
  };

  return (
    <div className="my-account-container">
      <NavBar />

      <form action="" className="form-container">
        <header>
          <h3>Dados da sua conta</h3>

          <Link to={"/home"} className="back-button">
            <FaArrowLeftLong />
            <span>Voltar</span>
          </Link>
        </header>

        <div className="input-container">
          <input type="text" placeholder="Digite o seu nome completo" />
        </div>

        <div className="input-container">
          <input type="text" placeholder="Digite o seu e-mail" />
        </div>

        <div className="input-container">
          <input
            id="input-password"
            type="password"
            placeholder="Digite a sua senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          {hidePassword ? (
            <span
              id="spanId"
              className="show-hide-password"
              onClick={handleShowOrHidePassword}
            >
              <AiOutlineEyeInvisible className="icon-show-hide" />
            </span>
          ) : (
            <span
              id="spanId"
              className="show-hide-password"
              onClick={handleShowOrHidePassword}
            >
              <AiOutlineEye className="icon-show-hide" />
            </span>
          )}
        </div>

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
        <button>Atualizar Dados</button>
      </form>
    </div>
  );
}
