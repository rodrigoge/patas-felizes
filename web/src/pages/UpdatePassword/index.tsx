import "./styles.scss";
import UpdatePasswordImage from "../../assets/Update-Password-image.png";
import Icon from "../../assets/favicon.svg";
import React, { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function UpdatePassword() {
  const navigate = useNavigate();
  const [token] = useState<string>("");
  const [email] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [hidePassword, setHidePassword] = useState(true);
  const [hideConfirmPassword, setHideConfirmPassword] = useState(true);

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

  async function handleShowOrHideConfirmPassword() {
    const passwordInputElement = document.getElementById(
      "input-confirm-password"
    ) as HTMLInputElement;

    if (passwordInputElement.type === "password") {
      passwordInputElement.type = "text";
      setHideConfirmPassword(false);
    } else {
      passwordInputElement.type = "password";
      setHideConfirmPassword(true);
    }
  }

  async function handleSubmitUpdatePassword(
    e: React.FormEvent<HTMLButtonElement>
  ) {
    e.preventDefault();

    try {
      if (password != confirmPassword) {
        toast.error("Senhas não coincidem");
      }
      const user = { email, password };
      const response = await api.post(
        `/v1/accounts/reset-password?token=${token}`,
        user
      );

      api.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${response.data.token}`;

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("isAuthenticated", JSON.stringify(true));

      toast.success("Senha atualizada com sucesso");

      navigate("/home");
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
    <div className="update-password-container">
      <img src={UpdatePasswordImage} />

      <form>
        <div className="header-container">
          <img src={Icon} />
          <span>Patas Felizes</span>

          <div className="description">
            Atualize sua senha para recuperar seu acesso.
          </div>
        </div>

        <div className="content-container">
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

          <div className="input-container">
            <input
              id="input-confirm-password"
              type="password"
              placeholder="Confirme a sua senha"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />

            {hideConfirmPassword ? (
              <span
                id="spanId"
                className="show-hide-password"
                onClick={handleShowOrHideConfirmPassword}
              >
                <AiOutlineEyeInvisible className="icon-show-hide" />
              </span>
            ) : (
              <span
                id="spanId"
                className="show-hide-password"
                onClick={handleShowOrHideConfirmPassword}
              >
                <AiOutlineEye className="icon-show-hide" />
              </span>
            )}
          </div>

          <button onClick={handleSubmitUpdatePassword}>Atualizar senha</button>
        </div>

        <Link to={"/"} className="home-link">
          Já possui um cadastro? Acesse a sua conta
        </Link>
      </form>
    </div>
  );
}
