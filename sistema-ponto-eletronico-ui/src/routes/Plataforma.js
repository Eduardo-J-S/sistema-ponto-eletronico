import React, { useContext } from "react";
import { Routes, Route } from "react-router-dom";

import RegistroPonto from "../pages/RegistroPonto";
import EspelhoDePonto from "../pages/EspelhoPonto";
import AlterarSenha from "../pages/AlterarSenha";
import { PerfilContext } from "../contexts";
import AdicionarFuncionario from "../pages/AdicionarFuncionario";
import Justificativa from "../pages/Justificativa";

const Plataforma = () => {
  const { user } = useContext(PerfilContext);
  return (
    <Routes>
      <Route path="/registro" element={<RegistroPonto />} />
      <Route path="/espelho" element={<EspelhoDePonto />} />
      <Route path="/alterar" element={<AlterarSenha />} />
      {user.role === 'admin' && (
        <Route path="/adicionar" element={<AdicionarFuncionario />} />
      )}
      {user.role === 'admin' && (
        <Route path="/justificativa" element={<Justificativa />} />
      )}
    </Routes>
  );
};

export default Plataforma;
