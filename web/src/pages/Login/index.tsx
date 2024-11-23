import "./styles.scss";
import LoginImage from "../../assets/Login-image.png";
import Icon from "../../assets/favicon.svg";
import { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Link } from "react-router-dom";

export default function Login() {
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

  return (
    <div className="login-container">
      <img src={LoginImage} />

      <form>
        <div className="header-container">
          <img src={Icon} />
          <span>Patas Felizes</span>

          <div className="description">
            Bem vindo(a) novamente, faça o seu login.
          </div>
        </div>

        <div className="content-container">
          <div className="input-container">
            <input type="text" placeholder="Digite o seu e-mail" />
          </div>

          <div className="input-container">
            <input
              id="input-password"
              type="password"
              placeholder="Digite a sua senha"
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

          <Link to={"/"} className="recovery-password-link">
            Esqueceu sua senha?
          </Link>

          <button>Login</button>
        </div>

        <Link to={"/"} className="register-account-link">
          Não possui conta? Crie uma agora mesmo
        </Link>
      </form>
    </div>
  );
}
