import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FaHome, FaClipboard, FaLock, FaQuestionCircle, FaSignOutAlt } from 'react-icons/fa';
import './styles.css'; // Adicione o CSS específico para o Drawer aqui

const Drawer = ({ isOpen, onClose }) => {
  // Fechar o drawer ao clicar fora dele
  useEffect(() => {
    const handleClickOutside = (event) => {
      const drawer = document.querySelector('.drawer');
      const menuIcon = document.querySelector('.menu-icon');
      if (drawer && !drawer.contains(event.target) && !menuIcon.contains(event.target)) {
        onClose();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [onClose]);

  return (
    <div className={`drawer ${isOpen ? 'open' : ''}`}>
      <nav className="drawer-nav">
        <Link to="/registro" className="drawer-link" onClick={onClose}>
          <FaHome /> Início
        </Link>
        <Link to="/espelho" className="drawer-link" onClick={onClose}>
          <FaClipboard /> Espelho de Ponto
        </Link>
        <Link to="/alterar-senha" className="drawer-link" onClick={onClose}>
          <FaLock /> Alterar Senha
        </Link>
        <Link to="/ajuda" className="drawer-link" onClick={onClose}>
          <FaQuestionCircle /> Ajuda
        </Link>
        <Link to="/sair" className="drawer-link" onClick={onClose}>
          <FaSignOutAlt /> Sair
        </Link>
      </nav>
    </div>
  );
};

export default Drawer;