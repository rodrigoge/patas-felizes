import "./styles.scss";
import RegisterAccountImage from "../../assets/Register-Account-image.png";
import Icon from "../../assets/favicon.svg";
import React, { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function RegisterAccount() {
  const navigate = useNavigate();
  const [name, setName] = useState<string>();
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

  async function handleSubmitRegister(e: React.FormEvent<HTMLButtonElement>) {
    e.preventDefault();

    try {
      const user = { name, email, password };
      await api.post("/accounts", user);
      toast.success("Cadastro efetuado com sucesso");
      navigate("/");
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(
          error.response?.data?.message || "Erro ao efetuar cadastro"
        );
      } else if (error instanceof Error) {
        toast.error(error.message || "Ocorreu um erro inesperado");
      } else {
        toast.error("Erro desconhecido");
      }
    }
  }

  return (
    <div className="register-account-container">
      <img src={RegisterAccountImage} />

      <form>
        <div className="header-container">
          <img src={Icon} />
          <span>Patas Felizes</span>

          <div className="description">Crie sua conta agora mesmo.</div>
        </div>

        <div className="content-container">
          <div className="input-container">
            <input
              type="text"
              placeholder="Digite o seu nome completo"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>

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

          <button onClick={handleSubmitRegister}>Cadastrar</button>
        </div>

        <Link to={"/"} className="home-link">
          Já possui um cadastro? Acesse a sua conta
        </Link>
      </form>
    </div>
  );
}
