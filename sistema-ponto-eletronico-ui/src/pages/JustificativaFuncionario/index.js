import React, { useState, useContext, useEffect } from "react";
import "./styles.css";
import { PerfilContext } from "../../contexts/";
import Drawer from "../../components/Drawer";
import { FaBars } from "react-icons/fa";

const JustificativaFuncionario = () => {
  const [justificativas, setJustificativas] = useState([]);
  const [tipo, setTipo] = useState("PERMISSAO"); // 'PERMISSAO' ou 'AUSENCIA'
  const [formData, setFormData] = useState({
    dataInicio: "",
    dataFim: "",
    tipoPermissao: "",
    tipoAusencia: "",
  });
  const { buscarJustificativasFuncionario, enviarPermissao, enviarAusencia } =
    useContext(PerfilContext);

  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  useEffect(() => {
    // Buscar justificativas anteriores do funcionário ao carregar a página
    const fetchJustificativas = async () => {
      try {
        const response = await buscarJustificativasFuncionario();
        if (response.success) {
          setJustificativas(response.response);
        } else {
          console.error("Erro ao buscar justificativas.");
        }
      } catch (error) {
        console.error("Erro ao buscar justificativas:", error);
      }
    };

    fetchJustificativas();
  }, [buscarJustificativasFuncionario]);

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
          ? await enviarPermissao(formData.dataInicio, formData.dataFim, formData.tipoPermissao)
          : await enviarAusencia(formData.tipoAusencia, formData.dataInicio, formData.dataFim);
      if (endpoint.success) {
        alert("Justificativa enviada com sucesso!");
      } else {
        alert("Erro ao enviar justificativa.");
      }
    } catch (error) {
      console.error("Erro ao enviar justificativa:", error);
    }
  };

  return (
    <div className="justificativaContainerFuncionario">
      <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}

        <h1 className="justificativaTitleFuncionario">Justificativas</h1>

        <div className="justificativaListFuncionario">
          <h2>Histórico de Justificativas</h2>
          {justificativas.length > 0 ? (
            <ul>
              {justificativas.map((justificativa, index) => (
                <li key={index}>
                  <strong>{justificativa.tipo}</strong>: {justificativa.descricao}
                  <br />
                  <strong>Período:</strong> {justificativa.dataInicio} a {justificativa.dataFim}
                </li>
              ))}
            </ul>
          ) : (
            <p>Nenhuma justificativa encontrada.</p>
          )}
        </div>

        <h2 className="justificativaTitleFuncionario">Nova Justificativa</h2>

        <form onSubmit={handleSubmit} className="justificativaActionFormFuncionario">
          <div className="justificativaActionFormRadioGroupFuncionario">
            <label className="justificativaActionFormRadioGroupLabelFuncionario">
              <input
                type="radio"
                value="PERMISSAO"
                checked={tipo === "PERMISSAO"}
                onChange={handleTipoChange}
              />
              Permissão
            </label>
            <label className="justificativaActionFormRadioGroupLabelFuncionario">
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
            className="justificativaActionFormInputFuncionario"
            value={formData.dataInicio}
            onChange={handleInputChange}
            placeholder="Data Início"
            required
          />
          <input
            type="date"
            name="dataFim"
            className="justificativaActionFormInputFuncionario"
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
              className="justificativaActionFormInputFuncionario"
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
              className="justificativaActionFormInputFuncionario"
              value={formData.tipoAusencia}
              onChange={handleInputChange}
              required
            >
              <option value="">Selecione uma Ausência</option>
              <option value="FALTA">Falta</option>
              <option value="ATESTADO">Atestado</option>
            </select>
          )}

          <button type="submit" className="justificativaActionFormButtonFuncionario">
            Enviar
          </button>
        </form>
      </div>
    </div>
  );
};

export default JustificativaFuncionario;
