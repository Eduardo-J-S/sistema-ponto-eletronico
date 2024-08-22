import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FaBars, FaHome, FaClipboard, FaLock, FaQuestionCircle, FaSignOutAlt } from 'react-icons/fa';
import './styles.css';

const RegistroPonto = () => {
  const [horaAtual, setHoraAtual] = useState(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
  const [dataAtual, setDataAtual] = useState(new Date().toLocaleDateString('pt-BR', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }));
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleEntrada = () => {
    alert('Entrada registrada às ' + horaAtual);
  };

  const handleSaida = () => {
    alert('Saída registrada às ' + horaAtual);
  };

  setInterval(() => {
    setHoraAtual(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
  }, 1000);

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

  return (
    <div className="app-container">
      <div className={`drawer ${isDrawerOpen ? 'open' : ''}`}>
        <nav className="drawer-nav">
          <Link to="/registro" className="drawer-link" onClick={closeDrawer}>
            <FaHome /> Início
          </Link>
          <Link to="/espelho" className="drawer-link" onClick={closeDrawer}>
            <FaClipboard /> Espelho de Ponto
          </Link>
          <Link to="/alterar-senha" className="drawer-link" onClick={closeDrawer}>
            <FaLock /> Alterar Senha
          </Link>
          <Link to="/ajuda" className="drawer-link" onClick={closeDrawer}>
            <FaQuestionCircle /> Ajuda
          </Link>
          <Link to="/sair" className="drawer-link" onClick={closeDrawer}>
            <FaSignOutAlt /> Sair
          </Link>
        </nav>
      </div>
      <div className="content">
        <header className="header">
          <FaBars className="menu-icon" onClick={toggleDrawer} />
          <h2>{dataAtual}</h2>
          <h1>{horaAtual}</h1>
        </header>
        <div className="botoes-container">
          <button onClick={handleEntrada} className="botao botao-entrada">
            ENTRADA
          </button>
          <button onClick={handleSaida} className="botao botao-saida">
            SAÍDA
          </button>
        </div>
      </div>
    </div>
  );
};

export default RegistroPonto;
