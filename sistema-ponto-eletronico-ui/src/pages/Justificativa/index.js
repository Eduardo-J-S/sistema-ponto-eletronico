import React, { useState, useContext } from "react";
import "./styles.css"; // Importa o CSS globalmente
import { PerfilContext } from "../../contexts/";
import Drawer from "../../components/Drawer";
import { FaBars } from "react-icons/fa";

const Justificativa = () => {
  const [cpfOrMatricula, setCpfOrMatricula] = useState("");
  const [funcionario, setFuncionario] = useState(null);
  const [tipo, setTipo] = useState("PERMISSAO"); // 'PERMISSAO' ou 'AUSENCIA'
  const [formData, setFormData] = useState({
    dataInicio: "",
    dataFim: "",
    tipoPermissao: "",
    tipoAusencia: "",
  });
  const { buscarFuncionarioCpfOuMatricula, ausencia, permissao } =
    useContext(PerfilContext);

  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const fetchFuncionario = async () => {
    try {
      const response = await buscarFuncionarioCpfOuMatricula(cpfOrMatricula);
      if (response.success) {
        setFuncionario(response.response);
      } else {
        alert("Funcionário não encontrado.");
      }
    } catch (error) {
      console.error("Erro ao buscar funcionário:", error);
    }
  };

  const handleTipoChange = (e) => {
    setTipo(e.target.value);
    setFormData({
      ...formData,
      tipoPermissao: tipo === "PERMISSAO" ? formData.tipoPermissao : "",
      tipoAusencia: tipo === "AUSENCIA" ? formData.tipoAusencia : "",
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const endpoint =
        tipo === "PERMISSAO"
          ? await permissao(
              formData.dataInicio,
              formData.dataFim,
              formData.tipoPermissao,
              funcionario.id
            )
          : await ausencia(
              formData.tipoAusencia,
              formData.dataInicio,
              formData.dataFim,
              funcionario.id
            );
      if (endpoint.success) {
        alert("Dados enviados com sucesso!");
      } else {
        alert("Erro ao enviar dados.");
      }
    } catch (error) {
      console.error("Erro ao enviar dados:", error);
    }
  };

  return (
    <div className="justificativaContainer">
      <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
        <h1 className="justificativaTitle">Gerenciar Funcionário</h1>
        <div className="justificativaSearchForm">
          <input
            type="text"
            className="justificativaSearchFormInput"
            placeholder="CPF ou Matrícula"
            value={cpfOrMatricula}
            onChange={(e) => setCpfOrMatricula(e.target.value)}
          />
          <button
            className="justificativaSearchFormButton"
            onClick={fetchFuncionario}
          >
            Buscar Funcionário
          </button>
        </div>

        {funcionario && (
          <div className="justificativaFuncionarioDetails">
            <h2 className="justificativaFuncionarioDetailsTitle">
              Funcionário Encontrado
            </h2>
            <p>
              <strong>Nome:</strong> {funcionario.nome}
            </p>
            <p>
              <strong>CPF:</strong> {funcionario.cpf}
            </p>
            <p>
              <strong>Matrícula:</strong> {funcionario.matricula}
            </p>

            <form onSubmit={handleSubmit} className="justificativaActionForm">
              <div className="justificativaActionFormRadioGroup">
                <label className="justificativaActionFormRadioGroupLabel">
                  <input
                    type="radio"
                    value="PERMISSAO"
                    checked={tipo === "PERMISSAO"}
                    onChange={handleTipoChange}
                  />
                  Permissão
                </label>
                <label className="justificativaActionFormRadioGroupLabel">
                  <input
                    type="radio"
                    value="AUSENCIA"
                    checked={tipo === "AUSENCIA"}
                    onChange={handleTipoChange}
                  />
                  Ausência
                </label>
              </div>

              <input
                type="date"
                name="dataInicio"
                className="justificativaActionFormInput"
                value={formData.dataInicio}
                onChange={handleInputChange}
                placeholder="Data Início"
                required
              />
              <input
                type="date"
                name="dataFim"
                className="justificativaActionFormInput"
                value={formData.dataFim}
                onChange={handleInputChange}
                placeholder="Data Fim"
                required
              />

              {tipo === "PERMISSAO" && (
                <select
                  name="tipoPermissao"
                  value={formData.tipoPermissao}
                  onChange={handleInputChange}
                  className="justificativaActionFormInput"
                  required
                >
                  <option value="">Selecione uma permissão</option>
                  <option value="FERIAS">Férias</option>
                  <option value="LICENÇA">Licença</option>
                  <option value="FOLGA">Folga</option>
                </select>
              )}

              {tipo === "AUSENCIA" && (
                <select
                  name="tipoAusencia"
                  className="justificativaActionFormInput"
                  value={formData.tipoAusencia}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">Selecione uma Ausência</option>
                  <option value="FALTA">Falta</option>
                  <option value="ATESTADO">Atestado</option>
                </select>
              )}

              <button type="submit" className="justificativaActionFormButton">
                Enviar
              </button>
            </form>
          </div>
        )}
      </div>
    </div>
  );
};

export default Justificativa;
