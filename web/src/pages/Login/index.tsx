import "./styles.scss";
import LoginImage from "../../assets/Login-image.png";
import Icon from "../../assets/favicon.svg";
import React, { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>();
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

  async function handleSubmitLogin(e: React.FormEvent<HTMLButtonElement>) {
    e.preventDefault();

    try {
      const user = { email, password };
      const response = await api.post("/v1/accounts/login", user);

      api.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${response.data.token}`;

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("isAuthenticated", JSON.stringify(true));

      toast.success("Login efetuado com sucesso");

      navigate("/");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.message || "Erro ao efetuar login");
      } else if (error instanceof Error) {
        toast.error(error.message || "Ocorreu um erro inesperado");
      } else {
        toast.error("Erro desconhecido");
      }
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
            <input
              type="text"
              placeholder="Digite o seu e-mail"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
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

          <Link to={"/"} className="recovery-password-link">
            Esqueceu sua senha?
          </Link>

          <button onClick={handleSubmitLogin}>Login</button>
        </div>

        <Link to={"/"} className="register-account-link">
          Não possui conta? Crie uma agora mesmo
        </Link>
      </form>
    </div>
  );
}
