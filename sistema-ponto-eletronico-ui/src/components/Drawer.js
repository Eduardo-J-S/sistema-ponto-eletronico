import React, { useEffect, useRef, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaHome, FaClipboard, FaLock, FaQuestionCircle, FaSignOutAlt, FaUserPlus, FaRegCalendarCheck, FaClock } from 'react-icons/fa';
import './styles.css';
import { PerfilContext } from '../contexts';

const Drawer = ({ isOpen, onClose }) => {
  const drawerRef = useRef(null);
  const { user, logout } = useContext(PerfilContext);
  const navigate = useNavigate(); 

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

  const handleLogout = () => {
    logout();
    navigate('/'); 
    onClose(); 
  };

  return (
    <div ref={drawerRef} className={`drawer ${isOpen ? 'open' : ''}`}>
      <nav className="drawer-nav">
        <Link to="/registro" className="drawer-link" onClick={onClose}>
          <FaHome /> Início
        </Link>
        <Link to="/espelho" className="drawer-link" onClick={onClose}>
          <FaClipboard /> Espelho de Ponto
        </Link>
        <Link to="/alterar" className="drawer-link" onClick={onClose}>
          <FaLock /> Alterar Senha
        </Link>
        <Link to="/ajuda" className="drawer-link" onClick={onClose}>
          <FaQuestionCircle /> Ajuda
        </Link>
        {user.role === 'admin' && (
          <Link to="/adicionar" className="drawer-link" onClick={onClose}>
            <FaUserPlus /> Adicionar Funcionário
          </Link>
        )}
        {user.role === 'admin' && (
          <Link to="/justificativa" className="drawer-link" onClick={onClose}>
          <FaRegCalendarCheck /> Justificativa Funcionário
          </Link>
        )}
        {user.role === 'admin' && (
          <Link to="/editarHorario" className="drawer-link" onClick={onClose}>
          <FaClock  /> Editar Horário
          </Link>
        )}
        <button className="drawer-link" onClick={handleLogout}>
          <FaSignOutAlt /> Sair
        </button>
      </nav>
    </div>
  );
};

export default Drawer;
