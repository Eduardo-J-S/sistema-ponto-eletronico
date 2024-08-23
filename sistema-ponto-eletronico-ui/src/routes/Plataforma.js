import React from "react";
import { Routes, Route } from "react-router-dom";

import RegistroPonto from "../pages/RegistroPonto";
import EspelhoDePonto from "../pages/EspelhoPonto";
import AlterarSenha from "../pages/AlterarSenha";

const Plataforma = () => {
  return (
    <Routes>
      <Route path="/registro" element={<RegistroPonto />} />
      <Route path="/espelho" element={<EspelhoDePonto />} />
      <Route path="/alterar" element={<AlterarSenha />} />
    </Routes>
  );
};

export default Plataforma;
