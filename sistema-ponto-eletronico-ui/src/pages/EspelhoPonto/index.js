import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaBars } from 'react-icons/fa';
import './styles.css';
import Drawer from '../../components/Drawer';

const EspelhoDePonto = () => {
  const [cpf, setCpf] = useState('');
  const [dataInicio, setDataInicio] = useState('');
  const [dataFim, setDataFim] = useState('');
  const [status, setStatus] = useState('Ativo');
  const [dadosPonto, setDadosPonto] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const closeDrawer = () => {
    setIsDrawerOpen(false);
  };

  // Fechar o drawer ao clicar fora dele
  useEffect(() => {
    const handleClickOutside = (event) => {
      const drawer = document.querySelector('.drawer');
      const menuIcon = document.querySelector('.menu-icon');
      if (drawer && !drawer.contains(event.target) && !menuIcon.contains(event.target)) {
        closeDrawer();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await axios.get(
        `http://localhost:8083/excel?cpf=${cpf}&dataInicio=${dataInicio}&dataFim=${dataFim}&status=${status}`
      );
      setDadosPonto(response.data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDataInicioChange = (e) => {
    setDataInicio(e.target.value);
    if (dataFim && e.target.value > dataFim) {
      setDataFim('');  // Reseta a data fim se a data início for posterior à data fim
    }
  };

  return (
   <div className="espelho-de-ponto-page">
  <Drawer isOpen={isDrawerOpen} onClose={closeDrawer} />
  <div className="content">
    {/* Renderiza o ícone do menu apenas se o Drawer estiver fechado */}
    {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
    <div className="espelho-de-ponto-container">
      <header className="header">
        
        <h2>Espelho de Ponto</h2>
      </header>
      <form onSubmit={handleSubmit} className="form-container">
        <div className="form-group">
          <label htmlFor="dataInicio">Data Início:</label>
          <input
            type="date"
            id="dataInicio"
            value={dataInicio}
            onChange={handleDataInicioChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dataFim">Data Fim:</label>
          <input
            type="date"
            id="dataFim"
            value={dataFim}
            onChange={(e) => setDataFim(e.target.value)}
            min={dataInicio}  // Restringe a data fim para ser após a data início
            required
          />
        </div>
        <button type="submit" className="botao-buscar">Buscar</button>
      </form>

      {loading && <p>Carregando...</p>}
      {error && <p>Erro ao carregar os dados: {error}</p>}

      {!loading && !error && dadosPonto.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Data</th>
              <th>Entrada</th>
              <th>Saída</th>
              <th>Horas Trabalhadas</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {dadosPonto.map((ponto, index) => (
              <tr key={index}>
                <td>{ponto.data}</td>
                <td>{ponto.entrada}</td>
                <td>{ponto.saida}</td>
                <td>{ponto.horasTrabalhadas}</td>
                <td>{ponto.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  </div>
</div>
  );
};

export default EspelhoDePonto;
