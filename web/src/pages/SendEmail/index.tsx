import "./styles.scss";
import SendEmailImage from "../../assets/Send-Email-image.png";
import Icon from "../../assets/favicon.svg";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";

export default function SendEmail() {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>("");

  async function handleSubmitSendEmail(e: React.FormEvent<HTMLButtonElement>) {
    e.preventDefault();

    try {
      const response = await api.post(`/v1/accounts/send/email?email=${email}`);

      api.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${response.data.token}`;

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("isAuthenticated", JSON.stringify(true));

      toast.success("E-mail enviado com sucesso");

      navigate("/reset-password");
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
    <div className="send-email-container">
      <img src={SendEmailImage} />

      <form>
        <div className="header-container">
          <img src={Icon} />
          <span>Patas Felizes</span>

          <div className="description">
            Envie um e-mail para recuperar o seu acesso.
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
          <button onClick={handleSubmitSendEmail}>Enviar e-mail</button>
        </div>

        <Link to={"/"} className="home-link">
          Já possui um cadastro? Acesse a sua conta
        </Link>
      </form>
    </div>
  );
}
