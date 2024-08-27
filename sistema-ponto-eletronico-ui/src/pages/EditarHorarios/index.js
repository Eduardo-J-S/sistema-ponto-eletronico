import React, { useContext, useState } from "react";
import "./styles.css"; // Certifique-se de importar o CSS específico
import { PerfilContext } from "../../contexts";
import Drawer from "../../components/Drawer";
import { FaBars } from "react-icons/fa";

function EditarHorarios() {
  const [cpf, setCpf] = useState("");
  const [horarios, setHorarios] = useState([]);
  const [isFuncionarioAdded, setIsFuncionarioAdded] = useState(true);
  const { buscarHorarios, atualizarHorario, deletarHorario, AdicionarHorario } =
    useContext(PerfilContext);

  const [funcionario, setFuncionario] = useState(null);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const buscarHorariosFuncionario = async () => {
    if (cpf.trim() === "") {
      alert("Campo vazio");
      return;
    }
    try {
      const response = await buscarHorarios(cpf);

      if (response.success) {
        setIsFuncionarioAdded(false);
        setHorarios(response.response);
        console.log(response.response);
        if (response.response.length > 0) {
          setFuncionario(response.response[0].funcionario);
          console.log(response.response[0].funcionario);
        } else {
          setFuncionario(null);
        }
      }
    } catch (error) {
      console.error("Erro ao buscar horários", error);
    }
  };

  const handleEdit = (index, field, value) => {
    const newHorarios = [...horarios];
    newHorarios[index][field] = value;
    setHorarios(newHorarios);
  };

  const salvarHorario = async (index) => {
    try {
      const horario = horarios[index];

      const horarioData = {
        diaSemana: horario.diaSemana, // Deve ser uma string como "MONDAY"
        horaInicio: horario.horaInicio,
        horaFim: horario.horaFim,
        funcionarioId: funcionario.id,
      };
      console.log("Dados enviados para o backend:", horarioData);
      if (horario.isNew) {
        const response = await AdicionarHorario(
          horario.diaSemana,
          horario.horaInicio,
          horario.horaFim,
          funcionario.id
        );
        if (response.success) {
          alert("Horário adicionado com sucesso!");
          const newHorarios = [...horarios];
          newHorarios[index] = response.response;
          setHorarios(newHorarios);
        }
      } else {
        const response = await atualizarHorario(
          horario.id,
          horario.diaSemana,
          horario.horaInicio,
          horario.horaFim,
          funcionario.id
        );
        if (response.success) {
          alert("Horário atualizado com sucesso!");
        }
      }
    } catch (error) {
      console.error("Erro ao salvar horário", error);
    }
  };

  const deletarHorarioFuncionario = async (index) => {
    try {
      const horario = horarios[index];
      const response = await deletarHorario(horario.id);
      const newHorarios = horarios.filter((_, i) => i !== index);
      if (response.success) {
        setHorarios(newHorarios);
        alert("Horário deletado com sucesso!");
      }
    } catch (error) {
      console.error("Erro ao deletar horário", error);
    }
  };

  const adicionarHorario = () => {
    const novoHorario = {
      diaSemana: "MONDAY", // Valor padrão para o novo horário
      horaInicio: "00:00",
      horaFim: "00:00",
      funcionario: funcionario.id,
      isNew: true, // Flag para identificar novos horários
    };
    setHorarios([...horarios, novoHorario]);
  };

  return (
    <div className="horario-page">
      <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
        <h2>Editar Horários do Funcionário</h2>
        <div className="cpf-input-container">
          <label htmlFor="cpf">CPF:</label>
          <input
            type="text"
            id="cpf"
            placeholder="Digite o CPF"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
          />
          <button onClick={buscarHorariosFuncionario}>Buscar</button>
        </div>

        {horarios.length > 0 ? (
          <div className="horarios-container">
            {horarios.map((horario, index) => (
              <div key={index} className="horario-item">
                <div className="horario-info">
                  {horario.isNew ? (
                    <select
                      value={horario.diaSemana}
                      onChange={(e) =>
                        handleEdit(index, "diaSemana", e.target.value)
                      }
                    >
                      <option value="MONDAY">Segunda-feira</option>
                      <option value="TUESDAY">Terça-feira</option>
                      <option value="WEDNESDAY">Quarta-feira</option>
                      <option value="THURSDAY">Quinta-feira</option>
                      <option value="FRIDAY">Sexta-feira</option>
                      <option value="SATURDAY">Sábado</option>
                      <option value="SUNDAY">Domingo</option>
                    </select>
                  ) : (
                    <span className="dia-semana">{horario.diaSemana}</span>
                  )}
                  <input
                    type="time"
                    value={horario.horaInicio}
                    onChange={(e) =>
                      handleEdit(index, "horaInicio", e.target.value)
                    }
                  />
                  <input
                    type="time"
                    value={horario.horaFim}
                    onChange={(e) =>
                      handleEdit(index, "horaFim", e.target.value)
                    }
                  />
                </div>
                <div className="actions">
                  <button
                    className="edit-btn"
                    onClick={() => salvarHorario(index)}
                  >
                    Salvar
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() => deletarHorarioFuncionario(index)}
                  >
                    Excluir
                  </button>
                </div>
              </div>
            ))}
            <button
              disabled={isFuncionarioAdded}
              className="add-btn"
              onClick={adicionarHorario}
            >
              Adicionar Novo Horário
            </button>
          </div>
        ) : (
          <div>
            <p className="no-horarios">
              Nenhum horário encontrado para este CPF.
            </p>
            <button className="add-btn" onClick={adicionarHorario}>
              Adicionar Novo Horário
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default EditarHorarios;
