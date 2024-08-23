import React, { useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import { FaHome, FaClipboard, FaLock, FaQuestionCircle, FaSignOutAlt } from 'react-icons/fa';
import './styles.css';

const Drawer = ({ isOpen, onClose }) => {
  const drawerRef = useRef(null);

  // Fechar o drawer ao clicar fora dele
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (drawerRef.current && !drawerRef.current.contains(event.target)) {
        onClose();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [onClose]);

  return (
    <div ref={drawerRef} className={`drawer ${isOpen ? 'open' : ''}`}>
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
