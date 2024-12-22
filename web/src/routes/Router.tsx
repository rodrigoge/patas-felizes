import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "../pages/Login";
import RegisterAccount from "../pages/RegisterAccount";
import SendEmail from "../pages/SendEmail";
import UpdatePassword from "../pages/UpdatePassword";
import Home from "../pages/Home";

export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register-account" element={<RegisterAccount />} />
        <Route path="/send-email" element={<SendEmail />} />
        <Route path="/reset-password" element={<UpdatePassword />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}
