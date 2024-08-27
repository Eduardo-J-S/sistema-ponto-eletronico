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
      localStorage.setItem("authToken", response.data.token);
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

  async function register(
    nomeEmpresa,
    cnpj,
    telefone,
    nomeAdmin,
    matriculaAdmin,
    senhaAdmin,
    cpfAdmin,
    emailAdmin
  ) {
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
        emailAdmin: emailAdmin,
      });
      setUser(response.data);
      localStorage.setItem("authToken", response.data.token);
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
    localStorage.removeItem("authToken");
  }

  async function registrarPonto(diaSemana, tipoRegistro) {
    alert(`Id do usuário ${user.id}`);
    try {
      const response = await api.post(
        "api/registroponto/v1",
        {
          diaSemana: diaSemana,
          funcionario: {
            id: user.id,
          },
          tipoRegistro: tipoRegistro,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true };
    } catch (error) {
      console.error("Erro ao registrar ponto:", error);
      alert("Ocorreu algum erro ao registrar o ponto.");
      return { success: false };
    }
  }

  async function AdicionarFuncionario(
    nome,
    telefone,
    email,
    matricula,
    dataNascimento,
    cpf,
    senha,
  ) {
    try {
      const response = await api.post(
        "api/funcionario/v1",
        {
          nome: nome,
          telefone: telefone,
          email: email,
          matricula: matricula,
          dataNascimento: dataNascimento,
          cpf: cpf,
          status: "Ativo",
          senha: senha,
          empresa: {
            id: user.empresaID,
          },
          role: "USER",
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true, response: response.data };
    } catch (error) {
      console.error("Erro ao cadastrar:", error);
      alert("Ocorreu algum erro ao cadastrar o funcionário.");
      return { success: false };
    }
  }

  async function AdicionarHorario(
    diaSemana,
    horaInicio,
    horaFim,
    funcionarioId
  ) {
    try {
      const response = await api.post(
        "api/horario/v1",
        {
          diaSemana: diaSemana,
          horaInicio: horaInicio,
          horaFim: horaFim,
          funcionario: {
            id: funcionarioId
          }
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true, response: response.data };
    } catch (error) {
      console.error("Erro ao cadastrar horario:", error);
      alert("Ocorreu algum erro ao cadastrar o horario.");
      return { success: false };
    }
  }

  async function buscarFuncionarioCpfOuMatricula(
    cpfOrMatricula
  ) {
    try {
      const response = await api.get(
        `api/funcionario/v1?cpfOrMatricula=${cpfOrMatricula}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true, response: response.data };
    } catch (error) {
      console.error("Erro ao buscar funcionário:", error);
      alert("Ocorreu algum erro buscar o funcionário.");
      return { success: false };
    }
  }

  async function ausencia(
    tipoAusencia,
    dataInicio,
    dataFim,
    funcionarioId
  ) {
    try {
      const response = await api.post(
        `api/ausencia/v1`,
        {
          tipoAusencia: tipoAusencia,
          dataInicio: dataInicio,
          dataFim: dataFim,
          funcionario: {
            id: funcionarioId
          }
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true };
    } catch (error) {
      console.error("Erro ao enviar uma ausência:", error);
      alert("Ocorreu algum erro ao enviar uma ausência.");
      return { success: false };
    }
  }

  async function permissao(
    dataInicio,
    dataFim,
    tipoPermissao,
    funcionarioId
  ) {
    try {
      const response = await api.post(
        `api/permissao/v1`,
        {
          dataInicio: dataInicio,
          dataFim: dataFim,
          tipoPermissao: tipoPermissao,
          funcionario: {
            id: funcionarioId
          }
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true };
    } catch (error) {
      console.error("Erro ao enivar uma permissão:", error);
      alert("Ocorreu algum erro ao enviar uma permissão.");
      return { success: false };
    }
  }

  async function baixarEspelhoDePonto(
    cpf,
    dataInicio,
    dataFim,
    status
  ) {
    try {
      const response = await api.get("excel",
        {
          params: {
            cpf: cpf,
            dataInicio: dataInicio,
            dataFim: dataFim,
            status: status,
          },
          headers: {
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
           responseType: 'blob'
        }
      );
      // Criar um link para o download
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `espelho_de_ponto_${cpf}.xls`); // Nome do arquivo
      document.body.appendChild(link);
      link.click();
      link.remove();

      return { success: true };
    } catch (error) {
      console.error("Erro ao baixar espelho de ponto:", error);
      alert("Ocorreu algum erro ao baixar o espelho de ponto.");
      return { success: false };
    }
  }

  async function buscarHorarios(
    cpfOrMatricula
  ) {
    try {
      const response = await api.get(`api/horario/v1`,
        {
        params: {
          cpfOrMatricula: cpfOrMatricula
        },
        headers: {
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          }
        }
      );

      return { success: true, response: response.data };
    } catch (error) {
      console.error("Erro ao buscar horarios:", error);
      alert("Ocorreu algum erro ao buscar os horarios.");
      return { success: false };
    }
  }

  async function atualizarHorario(
    id,
    diaSemana,
    horaInicio,
    horaFim,
    funcionarioId
  ) {
    try {
      const response = await api.post(
        `api/horario/v1`,
        {
          id: id,
          diaSemana: diaSemana,
          horaInicio: horaInicio,
          horaFim: horaFim,
          funcionario: {
            id: funcionarioId
          }
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true };
    } catch (error) {
      console.error("Erro ao atualizar um horario:", error);
      alert("Ocorreu algum erro ao atualizar um horario.");
      return { success: false };
    }
  }

  async function deletarHorario(
    id
  ) {
    try {
      const response = await api.delete(
        `api/horario/v1/${id}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true };
    } catch (error) {
      console.error("Erro ao deletar horario:", error);
      alert("Ocorreu algum erro ao deletar um horario.");
      return { success: false };
    }
  }


  async function buscarFuncionarosEmpresa() {
    try {
      const response = await api.get(
        `api/funcionario/v1/empresaId/${user.empresaID}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true, response: response.data };
    } catch (error) {
      console.error("Erro ao buscar funcionários:", error);
      alert("Ocorreu algum erro ao buscar os funcionários.");
      return { success: false };
    }
  }

  async function deletarFuncionario(id) {
    try {
      await api.delete(
        `api/funcionario/v1/${id}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("authToken")}`,
          },
        }
      );
      return { success: true};
    } catch (error) {
      console.error("Erro ao deletar funcionário:", error);
      alert("Ocorreu algum erro ao deletar o funcionário.");
      return { success: false };
    }
  }

  return (
    <PerfilContext.Provider
      value={{
        signed: !!user,
        user,
        register,
        login,
        logout,
        loading,
        registrarPonto,
        AdicionarFuncionario,
        AdicionarHorario,
        buscarFuncionarioCpfOuMatricula,
        ausencia,
        permissao,
        baixarEspelhoDePonto,
        buscarHorarios,
        atualizarHorario,
        deletarHorario,
        buscarFuncionarosEmpresa,
        deletarFuncionario
      }}
    >
      {children}
    </PerfilContext.Provider>
  );
};

export default ContextProvider;
