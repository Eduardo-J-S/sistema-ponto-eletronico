import React, { createContext, useState } from "react";
import api from "../service/api";

export const PerfilContext = createContext({});

const ContextProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(false);

  async function login(matricula, password) {
    setLoading(true);
    try {
      const response = await api.post("auth/login", {
        matricula: matricula,
        senha: password,
      });
      setUser(response.data);
      localStorage.setItem('authToken', response.data.token);
      console.log(response.data);
      setLoading(false);
      return { success: true };
    } catch (error) {
      console.error("Erro de login:", error);
      alert("Ocorreu algum erro.");
      setLoading(false);
      return { success: false };
    }
  }

  async function register(nomeEmpresa, cnpj, telefone, nomeAdmin, matriculaAdmin, senhaAdmin, cpfAdmin, emailAdmin) {
    setLoading(true);
    try {
      const response = await api.post("auth/register", {
        nomeEmpresa: nomeEmpresa,
        cnpj: cnpj,
        telefone: telefone,
        nomeAdmin: nomeAdmin,
        matriculaAdmin: matriculaAdmin,
        senhaAdmin: senhaAdmin,
        cpfAdmin: cpfAdmin,
        emailAdmin: emailAdmin
      });
      setUser(response.data);
      localStorage.setItem('authToken', response.data.token);
      console.log(response.data);
      setLoading(false);
      return { success: true };
    } catch (error) {
      console.error("Erro de login:", error);
      alert("Ocorreu algum erro.");
      setLoading(false);
      return { success: false };
    }
  }

  function logout() {
    setUser(null);
    localStorage.removeItem('authToken');
  }

  return (
    <PerfilContext.Provider value={{ signed: !!user, register, login, logout, loading }}>
      {children}
    </PerfilContext.Provider>
  );
};

export default ContextProvider;
