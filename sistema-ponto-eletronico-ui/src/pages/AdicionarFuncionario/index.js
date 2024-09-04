import React, { useState, useContext } from "react";
import { FaBars } from "react-icons/fa";
import "./styles.css";
import { PerfilContext } from "../../contexts";
import Drawer from "../../components/Drawer";

const AdicionarFuncionario = () => {
  const [funcionario, setFuncionario] = useState({
    nome: "",
    telefone: "",
    email: "",
    matricula: "",
    dataNascimento: "",
    cpf: "",
    status: "Ativo",
    senha: "",
    empresaId: "",
    role: "USER",
  });

  const [horario, setHorario] = useState({
    diaSemana: "",
    horaInicio: "",
    horaFim: "",
    funcionarioId: "",
  });

  const [isFuncionarioAdded, setIsFuncionarioAdded] = useState(false);
  const [isHorarioEnabled, setIsHorarioEnabled] = useState(false);

  const { AdicionarFuncionario, AdicionarHorario } = useContext(PerfilContext);

  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

    const toggleDrawer = () => {
      setIsDrawerOpen(!isDrawerOpen);
    };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFuncionario({ ...funcionario, [name]: value });
  };

  const handleHorarioChange = (e) => {
    const { name, value } = e.target;
    setHorario({ ...horario, [name]: value });
  };

  const handleSubmitFuncionario = async (e) => {
    e.preventDefault();
    try {
      const response = await AdicionarFuncionario(
        funcionario.nome,
        funcionario.telefone,
        funcionario.email,
        funcionario.matricula,
        funcionario.dataNascimento,
        funcionario.cpf,
        funcionario.senha
      );

      if (response.success) {
        setHorario({
          ...horario,
          funcionarioId: response.response.id,
        });
        setIsFuncionarioAdded(true);
        setIsHorarioEnabled(true);
        alert("Funcionário adicionado com sucesso!");
      }
    } catch (error) {
      console.error("Erro ao adicionar funcionário:", error);
    }
  };

  const handleSubmitHorario = async (e) => {
    e.preventDefault();
    try {
      const response = await AdicionarHorario(
        horario.diaSemana,
        horario.horaInicio,
        horario.horaFim,
        horario.funcionarioId
      );
      if (response.success) {
        alert("Horário adicionado com sucesso");
      }
    } catch (error) {
      console.error("Erro ao adicionar horário:", error);
    }
  };

  return (
    <div className="content">
       <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
      <div className="adicionar-funcionario-page">
        <h1>Adicionar Funcionário</h1>
        <form onSubmit={handleSubmitFuncionario}>
          <input
            type="text"
            name="nome"
            value={funcionario.nome}
            onChange={handleInputChange}
            placeholder="Nome"
            required
            disabled={isFuncionarioAdded}
          />
          <input
            type="text"
            name="telefone"
            value={funcionario.telefone}
            onChange={handleInputChange}
            placeholder="Telefone"
            required
          />
          <input
            type="email"
            name="email"
            value={funcionario.email}
            onChange={handleInputChange}
            placeholder="Email"
            required
            disabled={isFuncionarioAdded}
          />
          <input
            type="text"
            name="matricula"
            value={funcionario.matricula}
            onChange={handleInputChange}
            placeholder="Matrícula"
            required
            disabled={isFuncionarioAdded}
          />
          <input
            type="date"
            name="dataNascimento"
            value={funcionario.dataNascimento}
            onChange={handleInputChange}
            required
            disabled={isFuncionarioAdded}
          />
          <input
            type="text"
            name="cpf"
            value={funcionario.cpf}
            onChange={handleInputChange}
            placeholder="CPF"
            required
            disabled={isFuncionarioAdded}
          />
          <input
            type="password"
            name="senha"
            value={funcionario.senha}
            onChange={handleInputChange}
            placeholder="Senha"
            required
            disabled={isFuncionarioAdded}
          />
          <button type="submit" disabled={isFuncionarioAdded}>
            Adicionar Funcionário
          </button>
        </form>

        {isFuncionarioAdded && (
          <div className="user-info">
            <h2>Informações do Usuário</h2>
            <p>
              <strong>Funcionário ID:</strong> {horario.funcionarioId}
            </p>
            <p>
              <strong>Nome:</strong> {funcionario.nome}
            </p>
            <p>
              <strong>Email:</strong> {funcionario.email}
            </p>
            <p>
              <strong>Telefone:</strong> {funcionario.telefone}
            </p>
            <p>
              <strong>Email:</strong> {funcionario.matricula}
            </p>
            <p>
              <strong>Empresa:</strong> {funcionario.cpf}
            </p>
          </div>
        )}

        <h2>Adicionar Horário de Trabalho</h2>
        <form onSubmit={handleSubmitHorario}>
          <select
            name="diaSemana"
            value={horario.diaSemana}
            onChange={handleHorarioChange}
            disabled={!isHorarioEnabled}
          >
            <option value="">Selecione um dia</option>
            <option value="MONDAY">Segunda-feira</option>
            <option value="TUESDAY">Terça-feira</option>
            <option value="WEDNESDAY">Quarta-feira</option>
            <option value="THURSDAY">Quinta-feira</option>
            <option value="FRIDAY">Sexta-feira</option>
            <option value="SATURDAY">Sábado</option>
            <option value="SUNDAY">Domingo</option>
          </select>
          <input
            type="time"
            name="horaInicio"
            value={horario.horaInicio}
            onChange={handleHorarioChange}
            required
            disabled={!isHorarioEnabled}
          />
          <input
            type="time"
            name="horaFim"
            value={horario.horaFim}
            onChange={handleHorarioChange}
            required
            disabled={!isHorarioEnabled}
          />
          <button type="submit" disabled={!isHorarioEnabled}>
            Adicionar Horário
          </button>
        </form>
      </div>
      </div>
    </div>
  );
};

export default AdicionarFuncionario;
