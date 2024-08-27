import React, { useContext, useEffect, useState } from 'react';
import './styles.css';
import { PerfilContext } from '../../contexts';
import Drawer from '../../components/Drawer';
import { FaBars } from 'react-icons/fa';

const GerenciarFuncionarios = () => {
  const [funcionarios, setFuncionarios] = useState([]);
  const { buscarFuncionarosEmpresa, user, deletarFuncionario } = useContext(PerfilContext);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  useEffect(() => {
    const fetchData = async () => {
      const response = await buscarFuncionarosEmpresa();
      if (response.success) {
        setFuncionarios(response.response);
      }
    };

    fetchData();
  }, [user.empresaID]);

  const handleDelete = async (id) => {
    const response = await deletarFuncionario(id);
    if (response.success) {
      setFuncionarios(funcionarios.filter(funcionario => funcionario.id !== id));
    }
  };

  return (
    <div className="gerenciar-funcionarios-container">
        <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
      <h2>Gerenciar Funcionários</h2>
      {funcionarios.length === 0 ? (
        <p>Nenhum funcionário cadastrado.</p>
      ) : (
        <ul className="funcionarios-list">
          {funcionarios
            .filter(funcionario => funcionario.role === 'user') // Filtrando para mostrar apenas funcionários com role 'user'
            .map(funcionario => (
              <li key={funcionario.id} className="funcionario-item">
                <span><strong>Nome:</strong> {funcionario.nome}</span>
                <span><strong>CPF:</strong> {funcionario.cpf}</span>
                <span><strong>Matrícula:</strong> {funcionario.matricula}</span>
                <span><strong>Status:</strong> {funcionario.status}</span>
                <span><strong>Role:</strong> {funcionario.role}</span>
                <button onClick={() => handleDelete(funcionario.id)}>Excluir</button>
              </li>
            ))}
        </ul>
      )}
      </div>
    </div>
  );
};

export default GerenciarFuncionarios;
