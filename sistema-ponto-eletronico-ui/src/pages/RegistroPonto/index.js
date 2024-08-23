import React, { useState, useEffect } from 'react';
import { FaBars } from 'react-icons/fa';
import Drawer from '../../components/Drawer';
import './styles.css';

const RegistroPonto = () => {
  const [horaAtual, setHoraAtual] = useState(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
  const [dataAtual, setDataAtual] = useState(new Date().toLocaleDateString('pt-BR', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }));
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  // Atualiza a hora atual a cada segundo
  useEffect(() => {
    const interval = setInterval(() => {
      setHoraAtual(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  const handleEntrada = () => {
    alert('Entrada registrada às ' + horaAtual);
  };

  const handleSaida = () => {
    alert('Saída registrada às ' + horaAtual);
  };

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const closeDrawer = () => {
    setIsDrawerOpen(false);
  };

  return (
    <div className="registro-de-ponto-page">
      <Drawer isOpen={isDrawerOpen} onClose={closeDrawer} />
      <div className="content">
        {!isDrawerOpen && (
          <FaBars
            className="menu-icon"
            onClick={toggleDrawer}
          />
        )}
        <header className="header">
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
