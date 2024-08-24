import React, {  useState, useEffect, useContext } from 'react';
import { FaBars } from 'react-icons/fa';
import Drawer from '../../components/Drawer';
import './styles.css';
import { PerfilContext } from '../../contexts';

const RegistroPonto = () => {
  const [horaAtual, setHoraAtual] = useState(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
  const [dataAtual, setDataAtual] = useState(new Date().toLocaleDateString('pt-BR', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }));
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const { registrarPonto } = useContext(PerfilContext);

  // Função para obter o dia da semana em maiúsculas
  const getDayOfWeek = () => {
    const today = new Date();
    const daysOfWeek = [
      'SUNDAY',
      'MONDAY',
      'TUESDAY',
      'WEDNESDAY',
      'THURSDAY',
      'FRIDAY',
      'SATURDAY'
    ];
    return daysOfWeek[today.getDay()];
  };

  // Atualiza a hora atual a cada segundo
  useEffect(() => {
    const interval = setInterval(() => {
      setHoraAtual(new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' }));
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  const handleEntrada = async () => {
    alert('Entrada registrada às ' + horaAtual);
    await registrarPonto(getDayOfWeek(), 'INICIO');
  };
  
  const handleSaida = async () => {
    alert('Saída registrada às ' + horaAtual);
    await registrarPonto(getDayOfWeek(), 'FIM');
  };

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const closeDrawer = () => {
    setIsDrawerOpen(false);
  };

  return (
    <div className="registro-de-ponto-page">
      <div className="drawer-container">
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
    </div>
  );
};

export default RegistroPonto;
